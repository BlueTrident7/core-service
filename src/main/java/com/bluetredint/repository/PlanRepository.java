package com.bluetredint.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bluetredint.entity.Plans;

public interface PlanRepository extends JpaRepository<Plans, Long> {
}