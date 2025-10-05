package com.bluetrident.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluetrident.repository.IPaymentRepository;
import com.bluetrident.service.PaymentService;
import com.bluetrident.service.RazorpayService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private IPaymentRepository paymentRepository;

	@Autowired
	private RazorpayService razorpayService;

	private final ObjectMapper mapper = new ObjectMapper();

//	@Override
//	public String updatePaymentStatus(PaymentCallbackRequest request) {
//		return "Callback handled: " + request.getTransactionId();
//	}
//
//	@Override
//	public Map<String, Object> createRazorpayOrder(long amountInPaise, String receipt, Long userId) throws Exception {
//		Map<String, Object> order = razorpayService.createOrder(amountInPaise, "INR", receipt);
//		savePayment(order, userId, amountInPaise);
//		return order;
//	}

}