package com.bluetredint.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

import com.bluetredint.dto.PaymentCallbackRequest;
import com.bluetredint.service.PaymentService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@PostMapping("/callback")
	public String handlePaymentCallback(@RequestBody PaymentCallbackRequest request) {
		return paymentService.updatePaymentStatus(request);
	}
}
