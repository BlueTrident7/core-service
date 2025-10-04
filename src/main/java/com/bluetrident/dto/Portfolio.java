package com.bluetrident.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Portfolio {
	private double totalInvested;
	private double profitPercentage;
	private double profitAmount;
	private double totalAmount;
	private double balanceAmount;
	private String lastUpdated;
	private String accountNo;
}
