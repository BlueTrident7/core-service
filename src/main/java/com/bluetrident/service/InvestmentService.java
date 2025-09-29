package com.bluetrident.service;

import org.springframework.stereotype.Service;

import com.bluetrident.dto.InvestmentRequest;
import com.bluetrident.dto.InvestmentResponse;

@Service
public interface InvestmentService {

	InvestmentResponse initiateInvestment(InvestmentRequest request);

}
