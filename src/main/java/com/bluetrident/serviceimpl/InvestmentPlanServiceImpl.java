package com.bluetrident.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluetrident.dto.InvestmentPlanDTO;
import com.bluetrident.dto.InvestmentPlansDTO;
import com.bluetrident.dto.TransactionsDTO;
import com.bluetrident.entity.InvestmentPlans;
import com.bluetrident.enums.PlanType;
import com.bluetrident.jdbcTemplate.InvestmentPlanRepository;
import com.bluetrident.repository.InvestmentPlansRepository;
import com.bluetrident.service.InvestmentPlanService;

import jakarta.transaction.Transactional;

@Service
public class InvestmentPlanServiceImpl implements InvestmentPlanService {

	@Autowired
	private InvestmentPlansRepository repository;

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

	@Override
	public List<TransactionsDTO> getTransactionList(Long userId) {

		return null;
	}

}
