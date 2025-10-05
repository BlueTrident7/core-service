package com.bluetrident.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluetrident.dto.InvestmentGETDTO;
import com.bluetrident.dto.InvestmentPlanDTO;
import com.bluetrident.dto.InvestmentPlansDTO;
import com.bluetrident.dto.TransactionsDTO;
import com.bluetrident.entity.InvestmentPlans;
import com.bluetrident.enums.PlanType;
import com.bluetrident.jdbcTemplate.InvestmentPlanRepository;
import com.bluetrident.repository.IInvestmentPlansRepository;
import com.bluetrident.service.InvestmentPlanService;

import jakarta.transaction.Transactional;

@Service
public class InvestmentPlanServiceImpl implements InvestmentPlanService {

	@Autowired
	private IInvestmentPlansRepository repository;

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
		plan.setPlanPolicy(dto.getPlanPolicy());
		plan.setPlanType(PlanType.valueOf(dto.getPlanType()));
		plan.setLockPeriod(dto.getLockPeriod());
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
		plan.setPlanPolicy(dto.getPlanPolicy());
		plan.setPlanType(PlanType.valueOf(dto.getPlanType().toUpperCase()));
		plan.setLockPeriod(dto.getLockPeriod());
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
	public List<InvestmentGETDTO> getAllPlans() {
		return repository.findAll().stream().map(plan -> {
			InvestmentGETDTO dto = new InvestmentGETDTO();
			dto.setId(plan.getId());
			dto.setPlanName(plan.getPlanName());
			dto.setDescription(plan.getDescription());
			dto.setIdentifierCode(plan.getIdentifierCode());
			dto.setAmount(plan.getPrice());
			dto.setPlanPolicy(plan.getPlanPolicy());
			dto.setPlanType(plan.getPlanType().name());
			dto.setLockPeriod(plan.getLockPeriod());
			return dto;
		}).toList();
	}

	@Override
	public InvestmentGETDTO getPlanById(Long id) throws Exception {
		InvestmentPlans plan = repository.findById(id).orElseThrow(() -> new Exception("Plan not found"));

		InvestmentGETDTO dto = new InvestmentGETDTO();
		dto.setId(plan.getId());
		dto.setPlanName(plan.getPlanName());
		dto.setDescription(plan.getDescription());
		dto.setIdentifierCode(plan.getIdentifierCode());
		dto.setAmount(plan.getPrice());
		dto.setPlanPolicy(plan.getPlanPolicy());
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
