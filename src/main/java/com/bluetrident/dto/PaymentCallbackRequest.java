package com.bluetrident.dto;

import com.bluetrident.enums.PaymentStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentCallbackRequest {
	private String transactionId;
	private PaymentStatus status;
}
