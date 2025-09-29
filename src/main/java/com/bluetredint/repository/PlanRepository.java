package com.bluetredint.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bluetredint.entity.InvestmentPlans;

public interface PlanRepository extends JpaRepository<InvestmentPlans, Long> {
}