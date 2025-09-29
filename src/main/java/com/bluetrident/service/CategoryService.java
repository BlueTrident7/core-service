package com.bluetrident.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bluetrident.dto.CategoryPostDTO;

@Service
public interface CategoryService {

	CategoryPostDTO addCategory(CategoryPostDTO dto) throws Exception;

	List<CategoryPostDTO> getAllCategories();

	void deleteCategory(Long id) throws Exception;

	CategoryPostDTO updateCategory(Long id, CategoryPostDTO dto) throws Exception;

	CategoryPostDTO getCategoryById(Long id) throws Exception;

}
