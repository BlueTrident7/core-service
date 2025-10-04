package com.bluetrident.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bluetrident.dto.PortfolioResponse;
import com.bluetrident.service.PortfolioService;

@RestController
public class PortfolioController {

	@Autowired
	PortfolioService portfolioService;

	@GetMapping("/dashboard/{userId}")
	public PortfolioResponse getPortfolioData(@PathVariable("userId") Long userId) {
		return portfolioService.getPortfolioData(userId);
	}

}
