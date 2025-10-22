package com.bluetrident.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfileDTO {

	private Long id;
	private String fullName;
	private String userName;
	private String email;
	private String role;
	private String categoryName;
	private String categoryIdentifier;
	private String categoryDescription;
	private String phoneNumber;
	private boolean approvals;
	private String gender;
	private String age;
}
