package com.bluetrident.service;

import org.springframework.stereotype.Service;

import com.bluetrident.dto.PortfolioResponse;

@Service
public interface PortfolioService {

	PortfolioResponse getPortfolioData(Long userId);

}
