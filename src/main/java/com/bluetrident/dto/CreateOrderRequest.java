package com.bluetrident.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderRequest {

	private Long amount;
	private String receipt;
	private Long userId;
	private Long investmentId;
}
