package com.bluetrident.entity;

import com.bluetrident.enums.PlanType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "investment_plans")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class InvestmentPlans {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "plan_name", nullable = false)
	private String planName;

	@Column(name = "price", nullable = false)
	private double price;

	@Column(name = "identifier_code", nullable = false, unique = true)
	private String identifierCode;

	@Column(name = "description")
	private String description;

	@Column(name = "plan_details")
	private String planPolicy;

	@Enumerated(EnumType.STRING)
	private PlanType planType;

	private Long lockPeriod;

}
