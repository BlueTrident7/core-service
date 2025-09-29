package com.bluetrident.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bluetrident.entity.Plans;

public interface PlanRepository extends JpaRepository<Plans, Long> {
}