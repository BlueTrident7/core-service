package com.bluetredint.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bluetredint.entity.UserInvestment;

public interface UserInvestmentRepository extends JpaRepository<UserInvestment, Long> {
	List<UserInvestment> findByUserId(Long userId);

	List<UserInvestment> findByPlanId(Long planId);
}
