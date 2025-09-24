package com.bluetredint.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bluetredint.dto.InvestmentRequest;
import com.bluetredint.dto.InvestmentResponse;
import com.bluetredint.service.InvestmentService;

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
