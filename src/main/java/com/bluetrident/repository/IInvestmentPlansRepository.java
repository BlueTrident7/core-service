package com.bluetrident.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bluetrident.entity.InvestmentPlans;

public interface IInvestmentPlansRepository extends JpaRepository<InvestmentPlans, Long> {

}
