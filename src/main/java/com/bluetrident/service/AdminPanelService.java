package com.bluetrident.service;

import org.springframework.stereotype.Service;

import com.bluetrident.dto.AdminPanelDTO;

@Service
public interface AdminPanelService {

	AdminPanelDTO getAdminOverViewDetails(Long adminId);

}
