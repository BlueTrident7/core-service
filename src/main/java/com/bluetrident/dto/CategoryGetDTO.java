package com.bluetrident.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryGetDTO {
	private Long id;
	private String categoryName;
	private String description;
}
