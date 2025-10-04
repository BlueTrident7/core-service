package com.bluetrident.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bluetrident.dto.CategoryGetDTO;
import com.bluetrident.dto.CategoryPostDTO;
import com.bluetrident.entity.Category;

@Service
public interface CategoryService {

	public CategoryPostDTO addCategory(CategoryPostDTO dto) throws Exception;

	public List<CategoryGetDTO> getAllCategories();

	public void deleteCategory(Long id) throws Exception;

	public CategoryPostDTO updateCategory(Long id, CategoryPostDTO dto) throws Exception;

	public CategoryGetDTO getCategoryById(Long id) throws Exception;

	public Category getFindById(Long id) throws Exception;

}
