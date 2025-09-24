package com.bluetredint.service;

import com.bluetredint.dto.PaymentCallbackRequest;

public interface PaymentService {

	String updatePaymentStatus(PaymentCallbackRequest request);

}
