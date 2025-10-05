package com.bluetrident.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentDetailsDTO {
	private Long id;

	private String orderId;
	private String paymentId;
	private Long userId;
	private long amount;
	private String currency;
	private String status;
	private String key;
}
