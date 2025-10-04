package com.bluetrident.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InvestmentGETDTO {
	private Long id;
	private String planName;
	private String description;
	private String identifierCode;
	private double amount;
	private String planType;
	private String planPolicy;
	private Long lockPeriod;
}
