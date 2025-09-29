package com.bluetrident.service;

import java.util.List;

import com.bluetrident.dto.InvestmentPlanDTO;
import com.bluetrident.dto.InvestmentPlansDTO;

public interface InvestmentPlanService {

	InvestmentPlansDTO getPlanById(Long id) throws Exception;

	void deletePlan(Long id) throws Exception;

	InvestmentPlansDTO updatePlan(Long id, InvestmentPlansDTO dto) throws Exception;

	InvestmentPlansDTO addPlan(InvestmentPlansDTO dto);

	List<InvestmentPlansDTO> getAllPlans();

	List<InvestmentPlanDTO> getInvestmentPlans();

}
