package com.bluetrident.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvestmentRequest {
	private Long userId;
	private Long planId;
	private Double investedAmount;
}
