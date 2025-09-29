package com.bluetrident.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlansDTO {
	private String planName;
	private String categoryId;
	private double price;
	private String description;
}
