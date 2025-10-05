package com.bluetrident.service;

import java.util.List;

import com.bluetrident.dto.InvestmentGETDTO;
import com.bluetrident.dto.InvestmentPlanDTO;
import com.bluetrident.dto.InvestmentPlansDTO;
import com.bluetrident.dto.PaymentTransactionDTO;

public interface InvestmentPlanService {

	InvestmentGETDTO getPlanById(Long id) throws Exception;

	void deletePlan(Long id) throws Exception;

	InvestmentPlansDTO updatePlan(Long id, InvestmentPlansDTO dto) throws Exception;

	InvestmentPlansDTO addPlan(InvestmentPlansDTO dto);

	List<InvestmentGETDTO> getAllPlans();

	List<InvestmentPlanDTO> getInvestmentPlans();

	List<PaymentTransactionDTO> getTransactionList(Long userId);

}
