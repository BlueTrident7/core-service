package com.bluetredint.service;

import org.springframework.stereotype.Service;

import com.bluetredint.dto.AuthResponse;
import com.bluetredint.dto.LoginRequest;
import com.bluetredint.dto.RegisterRequest;

@Service
public interface AuthService {
	AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);

}
