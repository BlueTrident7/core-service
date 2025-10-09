package com.bluetrident.dto;

import com.bluetrident.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

	private String fullName;
	private String email;
	private String username;
	private String password;
	private String confirmPassword;
	private Role role;

	private String category;
	private String phoneNumber;

	private Long categoryId;

	private Boolean termsAccepted;
}
