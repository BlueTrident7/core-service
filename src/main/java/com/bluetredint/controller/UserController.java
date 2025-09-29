package com.bluetredint.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bluetredint.config.ApplicationResponse;
import com.bluetredint.config.CommonConstants;
import com.bluetredint.dto.UserProfileDTO;
import com.bluetredint.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/{id}")
	public ApplicationResponse<UserProfileDTO> getUserProfile(@PathVariable Long userId) {
		return new ApplicationResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK.value()),
				CommonConstants.OK, userService.getUserProfile(userId));
	}
}