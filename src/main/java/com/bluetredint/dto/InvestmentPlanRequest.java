package com.bluetredint.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class InvestmentPlanRequest {

	private String planName;
	private String description;
	private Double minAmount;
	private Double maxAmount;
}
