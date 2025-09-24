package com.bluetredint.serviceimpl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bluetredint.dto.AuthResponse;
import com.bluetredint.dto.LoginRequest;
import com.bluetredint.dto.RegisterRequest;
import com.bluetredint.entity.User;
import com.bluetredint.enums.Role;
import com.bluetredint.repository.UserRepository;
import com.bluetredint.security.JwtUtil;
import com.bluetredint.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil;

	@Override
	public AuthResponse register(RegisterRequest request) {
		if (userRepository.existsByUsername(request.getUserName())
				|| userRepository.existsByEmail(request.getEmail())) {
			throw new RuntimeException("User already exists");
		}

		User user = User.builder().fullName(request.getFullName()).email(request.getEmail())
				.password(passwordEncoder.encode(request.getPassword())).role(request.getRole())
				.category(request.getRole() == Role.USER ? request.getCategory() : null)
				.phoneNumber(request.getRole() == Role.USER ? request.getPhoneNumber() : null).build();
		userRepository.save(user);

		String token = jwtUtil.generateToken(user.getUserName());
		return new AuthResponse(token, user.getUserName());
	}

	@Override
	public AuthResponse login(LoginRequest request) {
		User user = userRepository.findByUsername(request.getUsername())
				.orElseThrow(() -> new RuntimeException("User not found"));

		if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
			throw new RuntimeException("Invalid credentials");
		}

		String token = jwtUtil.generateToken(user.getUserName());
		return new AuthResponse(token, user.getUserName());
	}

}
