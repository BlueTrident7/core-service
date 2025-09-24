package com.bluetredint.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bluetredint.dto.PlansDTO;
import com.bluetredint.entity.Plans;
import com.bluetredint.repository.PlanRepository;
import com.bluetredint.service.PlanService;

import jakarta.transaction.Transactional;

public class PlanServiceImpl implements PlanService {

	@Autowired
	private PlanRepository repository;

	@Transactional
	@Override
	public PlansDTO addPlan(PlansDTO dto) {
		Plans p = new Plans();
		p.setPlanName(dto.getPlanName());
		p.setPrice(dto.getPrice());
		p.setDescription(dto.getDescription());
		p.setIdentifierCode("PLAN-" + System.currentTimeMillis());
		repository.save(p);
		return dto;
	}

	@Override
	@Transactional
	public PlansDTO updatePlan(Long id, PlansDTO dto) throws Exception {
		Plans p = repository.findById(id).orElseThrow(() -> new Exception("Plan not found"));
		p.setPlanName(dto.getPlanName());
		p.setPrice(dto.getPrice());
		p.setDescription(dto.getDescription());
		repository.save(p);
		return dto;
	}

	@Override
	@Transactional
	public void deletePlan(Long id) throws Exception {
		Plans p = repository.findById(id).orElseThrow(() -> new Exception("Plan not found"));
		repository.delete(p);
	}

	@Override
	public List<PlansDTO> getAllPlans() {
		return repository.findAll().stream().map(p -> {
			PlansDTO dto = new PlansDTO();
			dto.setPlanName(p.getPlanName());
			dto.setPrice(p.getPrice());
			dto.setDescription(p.getDescription());
			return dto;
		}).toList();
	}

	@Override

	public PlansDTO getPlanById(Long id) throws Exception {
		Plans p = repository.findById(id).orElseThrow(() -> new Exception("Plan not found"));
		PlansDTO dto = new PlansDTO();
		dto.setPlanName(p.getPlanName());
		dto.setPrice(p.getPrice());
		dto.setDescription(p.getDescription());
		return dto;
	}
}
