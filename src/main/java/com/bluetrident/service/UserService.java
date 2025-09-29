package com.bluetrident.service;

import org.springframework.stereotype.Service;

import com.bluetrident.dto.UserProfileDTO;

@Service
public interface UserService {

	public UserProfileDTO getUserProfile(Long id);

}
