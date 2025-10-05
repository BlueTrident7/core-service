package com.bluetrident.service;

import org.springframework.stereotype.Service;

import com.bluetrident.dto.CreateOrderRequest;
import com.bluetrident.dto.MarkPaymentFailedRequest;
import com.bluetrident.dto.PaymentDetailsDTO;
import com.bluetrident.dto.VerifyPaymentRequest;

@Service
public interface RazorpayService {
//	Map<String, Object> createOrder(long amountInPaise, String currency, String receipt);

	public PaymentDetailsDTO createTransaction(CreateOrderRequest request);

	public VerifyPaymentRequest verifyAndUpdatePayment(VerifyPaymentRequest request);

	public MarkPaymentFailedRequest markPaymentAsFailed(MarkPaymentFailedRequest request);
}
