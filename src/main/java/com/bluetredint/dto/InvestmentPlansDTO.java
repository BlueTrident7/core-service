package com.bluetredint.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InvestmentPlansDTO {
	private String name;
	private String description;
	private String identifierCode;
	private double amount;
	private String planType;
	private String planDescription;
}
