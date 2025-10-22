package com.bluetrident.serviceimpl;

import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bluetrident.config.exception.ApplicationException;
import com.bluetrident.dto.AuthResponse;
import com.bluetrident.dto.LoginRequest;
import com.bluetrident.dto.RegisterRequest;
import com.bluetrident.entity.Category;
import com.bluetrident.entity.SystemMaster;
import com.bluetrident.entity.User;
import com.bluetrident.enums.Role;
import com.bluetrident.repository.IUserRepository;
import com.bluetrident.security.JwtUtil;
import com.bluetrident.service.AuthService;
import com.bluetrident.service.CategoryService;
import com.bluetrident.service.SystemMasterService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final IUserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil;
	private final CategoryService categoryService;
	private final SystemMasterService systemMasterService;

	@Override
	public AuthResponse register(RegisterRequest request) throws Exception {
		if (userRepository.existsByUserName(request.getUsername())
				|| userRepository.existsByEmail(request.getEmail())) {
			throw new Exception("User already exists");
		}

		Category category = null;
		if (request.getCategoryId() != null) {
			try {
				category = categoryService.getFindById(request.getCategoryId());
			} catch (Exception e) {
			}
		}
		SystemMaster gender = null;
		if(request.getGenderIdentifierCode()!=null) {
			gender =	this.systemMasterService.getGenderByCode(request.getGenderIdentifierCode());
		}
		

		User user = User.builder().fullName(request.getFullName()).userName(request.getUsername())
				.email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
				.role(request.getRole() == null ? Role.USER : request.getRole())
				.phoneNumber(request.getPhoneNumber()).gender(gender).age(request.getAge())
				.category(category).build();

		userRepository.save(user);

//		String token = jwtUtil.generateToken(user.getUserName());
		return new AuthResponse(null, user.getUserName());
	}

	@Override
	public AuthResponse login(LoginRequest request) {

		User user = userRepository.findByEmail(request.getEmail())
				.or(() -> userRepository.findByUserName(request.getEmail()))
				.orElseThrow(() -> new RuntimeException("User not found"));

		if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
			throw new RuntimeException("Invalid credentials");
		}

		 String accessToken = jwtUtil.generateToken(user); // short-lived
		    String refreshToken = jwtUtil.generateRefreshToken(user); 

		return new AuthResponse(accessToken, refreshToken);
	}

	@Override
	public AuthResponse refreshToken(String refreshToken) throws ApplicationException {
		 if (!jwtUtil.validateToken(refreshToken)) {
	            throw new RuntimeException("Invalid refresh token");
	        }

	        String username = jwtUtil.getUsernameFromToken(refreshToken);
	        User user = userRepository.findByUserName(username)
	                .orElseThrow(() -> new RuntimeException("User not found"));

	        // Generate new access token
	        String newAccessToken = jwtUtil.generateToken(user);

	        // Optional: generate new refresh token
	         String newRefreshToken = jwtUtil.generateRefreshToken(user);


			return new AuthResponse(newAccessToken, newRefreshToken);
	}
	 

}
