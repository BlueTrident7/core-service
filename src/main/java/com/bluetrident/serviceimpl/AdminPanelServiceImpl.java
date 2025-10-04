package com.bluetrident.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bluetrident.dto.AdminPanelDTO;
import com.bluetrident.jdbcTemplate.AdminPanelRepository;
import com.bluetrident.service.AdminPanelService;

@Component
public class AdminPanelServiceImpl implements AdminPanelService {

	@Autowired
	private AdminPanelRepository repository;

	@Override
	public AdminPanelDTO getAdminOverViewDetails(Long adminId) {

		AdminPanelDTO response = new AdminPanelDTO();
		response.setStats(repository.getStats());
		response.setUsers(repository.getUsers());
//		response.setSettings(repository.getSettings());
		response.setSettings(null);
		return response;
	}

}
