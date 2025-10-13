package com.bluetrident.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.bluetrident.config.ApplicationResponse;
import com.bluetrident.config.CommonConstants;
import com.bluetrident.dto.CreateOrderRequest;
import com.bluetrident.dto.MarkPaymentFailedRequest;
import com.bluetrident.dto.PaymentDetailsDTO;
import com.bluetrident.dto.VerifyPaymentRequest;
import com.bluetrident.entity.TokenPayload;
import com.bluetrident.service.RazorpayService;
import com.bluetrident.service.TokenService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor

public class RazorPayController {

	@Autowired
	RazorpayService razorpayService;
	
	@Autowired
	TokenService tokenService;
	

	@PostMapping("create-transaction")
	public ApplicationResponse<PaymentDetailsDTO> createTransaction( @RequestHeader HttpHeaders headers,@RequestBody CreateOrderRequest request) {
		try {
			TokenPayload tokenPayload = null;
			tokenPayload = this.tokenService.getTokenPayload(headers);
			return new ApplicationResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK.value()),
					CommonConstants.OK, razorpayService.createTransaction(request,tokenPayload));
		} catch (Exception e) {
			return new ApplicationResponse<>(CommonConstants.ERROR,
					String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), e.getMessage(), null);
		}
	}

	@PostMapping("verify")
	public ApplicationResponse<VerifyPaymentRequest> verifyPayment(@RequestBody VerifyPaymentRequest request) {
		try {
			return new ApplicationResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK.value()),
					CommonConstants.OK, razorpayService.verifyAndUpdatePayment(request));
		} catch (Exception e) {
			return new ApplicationResponse<>(CommonConstants.ERROR,
					String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), e.getMessage(), null);
		}
	}

	@PostMapping("payment/fail")
	public ApplicationResponse<MarkPaymentFailedRequest> markPaymentAsFailed(
			@RequestBody MarkPaymentFailedRequest request) {
		try {
			return new ApplicationResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK.value()),
					CommonConstants.OK, razorpayService.markPaymentAsFailed(request));
		} catch (Exception e) {
			return new ApplicationResponse<>(CommonConstants.ERROR,
					String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), e.getMessage(), null);
		}
	}
}
