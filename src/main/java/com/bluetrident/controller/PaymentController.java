package com.bluetrident.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

import com.bluetrident.service.PaymentService;

@RestController
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@Value("${razorpay.key-id}")
	private String keyId;

	@Value("${razorpay.key-secret}")
	private String keySecret;

	@Value("${razorpay.webhook-secret}")
	private String webhookSecret;

//	@PostMapping("/create-order")
//	public ApplicationResponse<Map<String, Object>> createOrder(@RequestBody CreateOrderRequest request) {
//		try {
//			Map<String, Object> order = paymentService.createRazorpayOrder(request.getAmount(), request.getReceipt(),
//					request.getUserId());
//
//			Map<String, Object> resp = Map.of("orderId", order.get("id"), "amount", order.get("amount"), "currency",
//					order.get("currency"), "key", keyId);
//
//			return new ApplicationResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK.value()),
//					CommonConstants.OK, resp);
//
//		} catch (Exception e) {
//			return new ApplicationResponse<>(CommonConstants.ERROR,
//					String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), e.getMessage(), null);
//		}
//	}
//
//	@PostMapping("/verify")
//	public ApplicationResponse<String> verifyPayment(@RequestBody PaymentVerificationRequest vreq) {
//		try {
//			boolean verified = paymentService.verifyPaymentSignature(vreq, keySecret);
//			if (verified) {
//				return new ApplicationResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK.value()),
//						"Payment verified successfully", "ok");
//			} else {
//				return new ApplicationResponse<>(CommonConstants.ERROR, String.valueOf(HttpStatus.BAD_REQUEST.value()),
//						"Signature mismatch", null);
//			}
//		} catch (Exception e) {
//			return new ApplicationResponse<>(CommonConstants.ERROR,
//					String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), e.getMessage(), null);
//		}
//	}
//
//	@PostMapping("/webhook")
//	public ApplicationResponse<String> handleWebhook(@RequestHeader("X-Razorpay-Signature") String signature,
//			@RequestBody String payload) {
//		try {
//			boolean valid = paymentService.verifyWebhookSignature(payload, signature, webhookSecret);
//			if (!valid) {
//				return new ApplicationResponse<>(CommonConstants.ERROR, String.valueOf(HttpStatus.BAD_REQUEST.value()),
//						"Invalid signature", null);
//			}
//
//			paymentService.processWebhook(payload);
//			return new ApplicationResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK.value()),
//					"Webhook processed successfully", "ok");
//
//		} catch (Exception e) {
//			return new ApplicationResponse<>(CommonConstants.ERROR,
//					String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), e.getMessage(), null);
//		}
//	}
}