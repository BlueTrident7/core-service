package com.bluetrident.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bluetrident.entity.UserInvestment;

public interface UserInvestmentRepository extends JpaRepository<UserInvestment, Long> {
	List<UserInvestment> findByUserId(Long userId);

	List<UserInvestment> findByPlanId(Long planId);
}
