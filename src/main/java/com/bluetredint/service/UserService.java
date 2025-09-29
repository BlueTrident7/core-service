package com.bluetredint.service;

import org.springframework.stereotype.Service;

import com.bluetredint.dto.UserProfileDTO;

@Service
public interface UserService {

	public UserProfileDTO getUserProfile(Long id);

}
