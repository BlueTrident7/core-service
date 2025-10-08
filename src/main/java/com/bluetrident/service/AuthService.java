package com.bluetrident.service;

import org.springframework.stereotype.Service;

import com.bluetrident.dto.AuthResponse;
import com.bluetrident.dto.LoginRequest;
import com.bluetrident.dto.RegisterRequest;

@Service
public interface AuthService {

	AuthResponse register(RegisterRequest request) throws Exception;

	AuthResponse login(LoginRequest request);

}
