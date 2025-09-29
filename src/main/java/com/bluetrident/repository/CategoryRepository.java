package com.bluetrident.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bluetrident.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	Optional<Category> findByIdentifierCode(String categoryName);

	Optional<Category> findByCategoryName(String categoryName);
}