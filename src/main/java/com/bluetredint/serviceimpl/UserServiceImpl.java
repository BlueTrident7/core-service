package com.bluetredint.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bluetredint.dto.UserProfileDTO;
import com.bluetredint.jdbcTemplate.UserRepository;
import com.bluetredint.service.UserService;

@Component
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;

	public UserProfileDTO getUserProfile(Long userId) {

		return repository.getUserProfileById(userId);
	}
}
