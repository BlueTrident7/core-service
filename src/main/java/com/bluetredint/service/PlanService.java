package com.bluetredint.service;

import java.util.List;

import com.bluetredint.dto.PlansDTO;

public interface PlanService {

	PlansDTO getPlanById(Long id) throws Exception;

	void deletePlan(Long id) throws Exception;

	PlansDTO updatePlan(Long id, PlansDTO dto) throws Exception;

	PlansDTO addPlan(PlansDTO dto);

	List<PlansDTO> getAllPlans();

}
