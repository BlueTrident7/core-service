package com.bluetredint.service;

import com.bluetredint.dto.InvestmentRequest;
import com.bluetredint.dto.InvestmentResponse;

public interface InvestmentService {

	InvestmentResponse initiateInvestment(InvestmentRequest request);

}
