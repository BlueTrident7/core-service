package com.bluetrident.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bluetrident.dto.PlansDTO;

@Service
public interface PlanService {

	PlansDTO getPlanById(Long id) throws Exception;

	void deletePlan(Long id) throws Exception;

	PlansDTO updatePlan(Long id, PlansDTO dto) throws Exception;

	PlansDTO addPlan(PlansDTO dto);

	List<PlansDTO> getAllPlans();

}
