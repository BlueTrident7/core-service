package com.bluetrident.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TokenPayload {
	private String fullName;
	private String username;
//	private String familyName;
	private String email;
	private Long customerBusinessId;
	private Long customerId;
	private Long siteId;
	private Long userId;

}
