package com.bluetrident.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bluetrident.dto.PortfolioResponse;
import com.bluetrident.jdbcTemplate.PortfolioRepository;
import com.bluetrident.service.PortfolioService;

@Component
public class PortfolioServiceImpl implements PortfolioService {

	@Autowired
	PortfolioRepository portfolioRepository;

	@Override
	public PortfolioResponse getPortfolioData(Long userId) {

		return portfolioRepository.getPortfolioData(userId);
	}

}
