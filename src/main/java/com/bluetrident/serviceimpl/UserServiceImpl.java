package com.bluetrident.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bluetrident.dto.UserProfileDTO;
import com.bluetrident.jdbcTemplate.UserRepository;
import com.bluetrident.service.UserService;

@Component
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;

	public UserProfileDTO getUserProfile(Long userId) {

		return repository.getUserProfileById(userId);
	}
}
