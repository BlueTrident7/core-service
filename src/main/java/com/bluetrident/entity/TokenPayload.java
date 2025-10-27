package com.bluetrident.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TokenPayload {
	private String fullName;
	private String username;
//	private String familyName;
	private String email;
	private Long customerBusinessId;
	private Long customerId;
	private Long siteId;
	private Long id;

}
