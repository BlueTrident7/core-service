package com.bluetrident.serviceimpl;

import java.time.LocalDateTime;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bluetrident.dto.CreateOrderRequest;
import com.bluetrident.dto.MarkPaymentFailedRequest;
import com.bluetrident.dto.PaymentDetailsDTO;
import com.bluetrident.dto.VerifyPaymentRequest;
import com.bluetrident.entity.InvestmentPlans;
import com.bluetrident.entity.Payment;
import com.bluetrident.entity.PaymentTransaction;
import com.bluetrident.entity.User;
import com.bluetrident.entity.UserInvestmentPlans;
import com.bluetrident.enums.InvestmentStatus;
import com.bluetrident.enums.PaymentStatus;
import com.bluetrident.repository.IInvestmentPlansRepository;
import com.bluetrident.repository.IPaymentRepository;
import com.bluetrident.repository.IPaymentTransactionRepository;
import com.bluetrident.repository.IUserInvestmentPlansRepository;
import com.bluetrident.repository.IUserRepository;
import com.bluetrident.service.RazorpayService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;

@Service
public class RazorpayServiceImpl implements RazorpayService {

	@Value("${razorpay.key-id}")
	private String KEY;

	@Value("${razorpay.key-secret}")
	private String SECRET_KEY;

	@Autowired
	private IPaymentRepository paymentRepository;

	@Autowired
	private IPaymentTransactionRepository transactionRepository;

	@Autowired
	private IUserInvestmentPlansRepository iUserInvestmentPlansRepository;

	@Autowired
	private IUserRepository iUserRepository;

	@Autowired
	private IInvestmentPlansRepository iInvestmentPlansRepository;

//	private final RestTemplate restTemplate = new RestTemplate();
//
//	@Override
//	public Map<String, Object> createOrder(long amountInPaise, String currency, String receipt) {
//		String url = "https://api.razorpay.com/v1/orders";
//
//		String auth = keyId + ":" + keySecret;
//		String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));
//
//		HttpHeaders headers = new HttpHeaders();
//		headers.set("Authorization", "Basic " + encodedAuth);
//		headers.setContentType(MediaType.APPLICATION_JSON);
//
//		Map<String, Object> body = new HashMap<>();
//		body.put("amount", amountInPaise);
//		body.put("currency", currency);
//		body.put("receipt", receipt);
//
//		HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);
//
//		ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);
//		return response.getBody();
//	}

	@Override
	public PaymentDetailsDTO createTransaction(CreateOrderRequest request) {
		try {
			// 1️⃣ Fetch User
			User user = iUserRepository.findById(request.getUserId())
					.orElseThrow(() -> new RuntimeException("User not found"));

			// 2️⃣ Fetch Investment Plan
			InvestmentPlans investmentPlans = iInvestmentPlansRepository.findById(request.getInvestmentId())
					.orElseThrow(() -> new RuntimeException("Investment plan not found"));

			// 4️⃣ Create Razorpay order
			RazorpayClient razorpayClient = new RazorpayClient(KEY, SECRET_KEY);

			JSONObject jsonObject = new JSONObject();
			jsonObject.put("amount", request.getAmount() * 100); // amount in paise
			jsonObject.put("currency", "INR");
			jsonObject.put("receipt", "INVESTMENT_" + request.getInvestmentId());

			Order order = razorpayClient.orders.create(jsonObject);

			// 3️⃣ Create or fetch UserInvestmentPlans
			UserInvestmentPlans userInvestmentPlan = UserInvestmentPlans.builder().user(user).plan(investmentPlans)
					.status(InvestmentStatus.PENDING) // pending until payment success
					.build();

			iUserInvestmentPlansRepository.save(userInvestmentPlan);

			// 5️⃣ Save Payment entity
			Payment payment = Payment.builder().razorPayOrderId(order.get("id")).user(user).orderId(order.get("id"))
					.investment(userInvestmentPlan).amount(((Number) order.get("amount")).longValue())
					.currency((String) order.get("currency")).status(PaymentStatus.CREATED).build();

			paymentRepository.save(payment);

			// 6️⃣ Prepare DTO for frontend
			PaymentDetailsDTO paymentDTO = new PaymentDetailsDTO();
			paymentDTO.setKey(KEY);
			paymentDTO.setAmount(((Number) order.get("amount")).intValue());
			paymentDTO.setCurrency((String) order.get("currency"));
			paymentDTO.setOrderId(order.get("id"));

			return paymentDTO;

		} catch (RazorpayException e) {
			throw new RuntimeException("Error creating Razorpay order: " + e.getMessage(), e);
		}
	}

	@Override
	public VerifyPaymentRequest verifyAndUpdatePayment(VerifyPaymentRequest request) {
		try {
			RazorpayClient razorpay = new RazorpayClient(KEY, SECRET_KEY);

			// ✅ 1️⃣ Verify signature
			JSONObject attributes = new JSONObject();
			attributes.put("razorpay_order_id", request.getRazorpayOrderId());
			attributes.put("razorpay_payment_id", request.getRazorpayPaymentId());
			attributes.put("razorpay_signature", request.getRazorpaySignature());

			boolean isSignatureValid = Utils.verifyPaymentSignature(attributes, SECRET_KEY);
			Payment payment = paymentRepository.findByRazorPayOrderId(request.getRazorpayOrderId());

			if (!isSignatureValid) {
				payment.setStatus(PaymentStatus.FAILED);
				paymentRepository.save(payment);
				throw new RuntimeException("Invalid payment signature");
			}

			// ✅ 2️⃣ Fetch Razorpay payment details
			com.razorpay.Payment razorPayment = razorpay.payments.fetch(request.getRazorpayPaymentId());

			String status = razorPayment.get("status");
			String method = razorPayment.get("method");

			// ✅ 3️⃣ Update Payment entity
			payment.setRazorPayPaymentId(request.getRazorpayPaymentId());
			payment.setPaymentMethod(method);
			payment.setStatus(status.equals("captured") ? PaymentStatus.SUCCESS : PaymentStatus.FAILED);
			paymentRepository.save(payment);

			// ✅ 4️⃣ Record PaymentTransaction
			double amount = ((Number) razorPayment.get("amount")).doubleValue() / 100.0;

			PaymentTransaction transaction = PaymentTransaction.builder().payment(payment)
					.transactionId(request.getRazorpayPaymentId()).amount(amount).paymentMethod(method)
					.status(status.equals("captured") ? PaymentStatus.SUCCESS : PaymentStatus.FAILED)
					.createdAt(LocalDateTime.now()).build();

			transactionRepository.save(transaction);

			return request;

		} catch (Exception e) {
			throw new RuntimeException("Error verifying payment: " + e.getMessage(), e);
		}
	}

	@Override
	public MarkPaymentFailedRequest markPaymentAsFailed(MarkPaymentFailedRequest request) {

		Payment payment = paymentRepository.findByOrderId(request.getOrderId());

		if (payment == null) {
			throw new RuntimeException("Payment not found for orderId: " + request.getOrderId());
		}

		// Update payment status
		payment.setStatus(PaymentStatus.FAILED);
		payment.setErrorDescription(request.getReason() != null ? request.getReason() : "User dismissed payment");
		paymentRepository.save(payment);

		// Also update UserInvestmentPlans status if needed
		UserInvestmentPlans investment = payment.getInvestment();
		if (investment != null) {
			investment.setStatus(InvestmentStatus.CANCELLED); // or FAILED if that makes more sense
			iUserInvestmentPlansRepository.save(investment);
		}
		return request;
	}

}