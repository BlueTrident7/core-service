package com.bluetrident.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Plan {

	private Long id;
	private String name;
	private double rate;
	private String description;
	private String type; // Daily, Weekly, Monthly, Yearly
}
