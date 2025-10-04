package com.bluetrident.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Charts {
	private List<String> months;
	private List<Double> monthlyRevenue;
	private List<Double> cashIn;
	private List<Double> cashOut;
}
