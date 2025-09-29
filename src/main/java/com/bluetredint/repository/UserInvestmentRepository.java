package com.bluetredint.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bluetredint.entity.UserInvestmentPlans;

public interface UserInvestmentRepository extends JpaRepository<UserInvestmentPlans, Long> {
	List<UserInvestmentPlans> findByUserId(Long userId);

	List<UserInvestmentPlans> findByPlanId(Long planId);
}
