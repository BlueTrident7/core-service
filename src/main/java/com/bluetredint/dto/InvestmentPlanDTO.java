package com.bluetredint.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvestmentPlanDTO {

	private Long id;
	private String planName;
	private double price;
	private String identifierCode;
	private String description;
	private String planPolicy;
}
