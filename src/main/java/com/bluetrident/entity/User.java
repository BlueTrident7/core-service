package com.bluetrident.entity;

import com.bluetrident.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String fullName;

	private String userName;

	@Column(unique = true, nullable = false)
	private String email;

	private String password;

	@Enumerated(EnumType.STRING)
	private Role role;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private Category category;
	
	private String phoneNumber;

	private boolean approvals;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gender_id", referencedColumnName = "id")
	@JsonIgnore
	private SystemMaster gender;
	
	private Integer age;
	
}
