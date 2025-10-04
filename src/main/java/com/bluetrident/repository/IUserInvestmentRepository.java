package com.bluetrident.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bluetrident.entity.UserInvestmentPlans;

public interface IUserInvestmentRepository extends JpaRepository<UserInvestmentPlans, Long> {
	List<UserInvestmentPlans> findByUserId(Long userId);

	List<UserInvestmentPlans> findByPlanId(Long planId);
}
