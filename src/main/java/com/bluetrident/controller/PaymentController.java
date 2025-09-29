package com.bluetrident.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bluetrident.dto.PaymentCallbackRequest;
import com.bluetrident.service.PaymentService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@PostMapping("/callback")
	public String handlePaymentCallback(@RequestBody PaymentCallbackRequest request) {
		return paymentService.updatePaymentStatus(request);
	}
}
