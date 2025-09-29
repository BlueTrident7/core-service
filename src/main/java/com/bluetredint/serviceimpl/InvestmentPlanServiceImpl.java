package com.bluetredint.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluetredint.dto.InvestmentPlanDTO;
import com.bluetredint.dto.InvestmentPlansDTO;
import com.bluetredint.entity.InvestmentPlans;
import com.bluetredint.enums.PlanType;
import com.bluetredint.jdbcTemplate.InvestmentPlanRepository;
import com.bluetredint.repository.PlanRepository;
import com.bluetredint.service.InvestmentPlanService;

import jakarta.transaction.Transactional;

@Service
public class InvestmentPlanServiceImpl implements InvestmentPlanService {

	@Autowired
	private PlanRepository repository;

	@Autowired
	private InvestmentPlanRepository investmentPlanRepository;

	@Transactional
	@Override
	public InvestmentPlansDTO addPlan(InvestmentPlansDTO dto) {
		InvestmentPlans plan = new InvestmentPlans();
		plan.setPlanName(dto.getName());
		plan.setPrice(dto.getAmount());
		plan.setDescription(dto.getDescription());
		plan.setIdentifierCode("PLAN-" + System.currentTimeMillis());
		plan.setPlanPlolicy(dto.getPlanDescription());
		plan.setPlanType(PlanType.valueOf(dto.getPlanType()));
		repository.save(plan);

		dto.setIdentifierCode(plan.getIdentifierCode());
		return dto;
	}

	@Override
	@Transactional
	public InvestmentPlansDTO updatePlan(Long id, InvestmentPlansDTO dto) throws Exception {
		InvestmentPlans plan = repository.findById(id).orElseThrow(() -> new Exception("Plan not found"));

		plan.setPlanName(dto.getName());
		plan.setPrice(dto.getAmount());
		plan.setDescription(dto.getDescription());
		plan.setPlanPlolicy(dto.getPlanDescription());
		plan.setPlanType(PlanType.valueOf(dto.getPlanType().toUpperCase()));
		repository.save(plan);

		dto.setIdentifierCode(plan.getIdentifierCode());
		return dto;
	}

	@Override
	@Transactional
	public void deletePlan(Long id) throws Exception {
		InvestmentPlans plan = repository.findById(id).orElseThrow(() -> new Exception("Plan not found"));
		repository.delete(plan);
	}

	@Override
	public List<InvestmentPlansDTO> getAllPlans() {
		return repository.findAll().stream().map(plan -> {
			InvestmentPlansDTO dto = new InvestmentPlansDTO();
			dto.setName(plan.getPlanName());
			dto.setDescription(plan.getDescription());
			dto.setIdentifierCode(plan.getIdentifierCode());
			dto.setAmount(plan.getPrice());
			dto.setPlanDescription(plan.getPlanPlolicy());
			dto.setPlanType(plan.getPlanType().name());
			return dto;
		}).toList();
	}

	@Override
	public InvestmentPlansDTO getPlanById(Long id) throws Exception {
		InvestmentPlans plan = repository.findById(id).orElseThrow(() -> new Exception("Plan not found"));

		InvestmentPlansDTO dto = new InvestmentPlansDTO();
		dto.setName(plan.getPlanName());
		dto.setDescription(plan.getDescription());
		dto.setIdentifierCode(plan.getIdentifierCode());
		dto.setAmount(plan.getPrice());
		dto.setPlanDescription(plan.getPlanPlolicy());
		dto.setPlanType(plan.getPlanType().name());
		return dto;
	}

	@Override
	public List<InvestmentPlanDTO> getInvestmentPlans() {
		return investmentPlanRepository.getAllPlans();
	}
}
