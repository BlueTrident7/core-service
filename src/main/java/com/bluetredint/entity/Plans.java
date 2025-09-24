package com.bluetredint.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "plans")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Plans {

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

}
