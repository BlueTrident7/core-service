package com.bluetrident.serviceimpl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bluetrident.dto.AuthResponse;
import com.bluetrident.dto.LoginRequest;
import com.bluetrident.dto.RegisterRequest;
import com.bluetrident.entity.Category;
import com.bluetrident.entity.User;
import com.bluetrident.enums.Role;
import com.bluetrident.repository.IUserRepository;
import com.bluetrident.security.JwtUtil;
import com.bluetrident.service.AuthService;
import com.bluetrident.service.CategoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final IUserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil;

	private final CategoryService categoryService;

	@Override
	public AuthResponse register(RegisterRequest request) {

		if (userRepository.existsByUsername(request.getUsername())
				|| userRepository.existsByEmail(request.getEmail())) {
			throw new RuntimeException("User already exists");
		}
		Category category = null;
		try {
			category = categoryService.getFindById(request.getCategoryId());
		} catch (Exception e) {
		}

		User user = User.builder().fullName(request.getFullName()).userName(request.getUsername()) // ← add this line
				.email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
				.role(request.getRole()).category(category)
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
