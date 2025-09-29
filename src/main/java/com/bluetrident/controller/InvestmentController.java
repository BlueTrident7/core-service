package com.bluetrident.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bluetrident.dto.InvestmentRequest;
import com.bluetrident.dto.InvestmentResponse;
import com.bluetrident.service.InvestmentService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class InvestmentController {

	@Autowired
	private InvestmentService investmentService;

	@PostMapping("/invest")
	public InvestmentResponse invest(@RequestBody InvestmentRequest request) {
		return investmentService.initiateInvestment(request);
	}
}
