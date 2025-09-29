package com.bluetrident.service;

import org.springframework.stereotype.Service;

import com.bluetrident.dto.PaymentCallbackRequest;

@Service
public interface PaymentService {

	String updatePaymentStatus(PaymentCallbackRequest request);

}
