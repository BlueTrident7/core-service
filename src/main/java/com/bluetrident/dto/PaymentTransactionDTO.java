package com.bluetrident.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentTransactionDTO {

	private Long transactionId;
	private String externalTransactionId;
	private Double amount;
	private String transactionStatus;
	private String paymentMethod;
	private String gatewayResponse;
	private String remarks;
	private Date createdAt;

	private Long paymentId;
	private String orderId;
	private String paymentStatus;
	private String errorDescription;

	private Long investmentId;
	private String investmentStatus;

	private Long userId;
	private String fullName;
	private String email;

	private String transactionType;
}
