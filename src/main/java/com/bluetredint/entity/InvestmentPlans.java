package com.bluetredint.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "investment_plans")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvestmentPlans {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String planName;
	private String description;
	private Double minAmount;
	private Double maxAmount;
}