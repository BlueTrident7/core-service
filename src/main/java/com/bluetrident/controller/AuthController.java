package com.bluetrident.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bluetrident.config.ApplicationResponse;
import com.bluetrident.config.CommonConstants;
import com.bluetrident.config.exception.ApplicationException;
import com.bluetrident.dto.AuthResponse;
import com.bluetrident.dto.LoginRequest;
import com.bluetrident.dto.RegisterRequest;
import com.bluetrident.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;

	@PostMapping("/register")
	public ApplicationResponse<AuthResponse> register(@RequestBody RegisterRequest request) {
		try {
			AuthResponse authResponse = authService.register(request);
			return new ApplicationResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK.value()),
					CommonConstants.OK, authResponse);
		} catch (ApplicationException e) {
			return new ApplicationResponse<>(CommonConstants.ERROR, String.valueOf(HttpStatus.BAD_REQUEST.value()),
					e.getMessage(), null);
		} catch (Exception e) {
			return new ApplicationResponse<>(CommonConstants.ERROR,
					String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), "Registration failed: " + e.getMessage(),
					null);
		}
	}

	@PostMapping("/login")
	public ApplicationResponse<AuthResponse> login(@RequestBody LoginRequest request) throws ApplicationException {
		try {
			AuthResponse authResponse = authService.login(request);
			return new ApplicationResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK.value()),
					CommonConstants.OK, authResponse);

		} catch (Exception e) {
			return new ApplicationResponse<>(CommonConstants.ERROR,
					String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), "Registration failed: " + e.getMessage(),
					null);
		}
	}
	@PostMapping("/refresh-token")
	public ApplicationResponse<AuthResponse> refreshToken(@RequestBody Map<String, String> request) {
	    try {
	        String refreshToken = request.get("refreshToken");
	        AuthResponse authResponse = authService.refreshToken(refreshToken);

	        return new ApplicationResponse<>(
	                CommonConstants.SUCCESS,
	                String.valueOf(HttpStatus.OK.value()),
	                CommonConstants.OK,
	                authResponse
	        );

	    } catch (Exception e) {
	        return new ApplicationResponse<>(
	                CommonConstants.ERROR,
	                String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
	                "Token refresh failed: " + e.getMessage(),
	                null
	        );
	    }
	}

}
