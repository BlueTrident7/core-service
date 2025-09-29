package com.bluetredint.service;

import java.util.List;

import com.bluetrident.dto.CategoryPostDTO;
import com.bluetrident.entity.Category;

public interface CategoryService {

	public CategoryPostDTO addCategory(CategoryPostDTO dto) throws Exception;

	public List<CategoryPostDTO> getAllCategories();

	public void deleteCategory(Long id) throws Exception;

	public CategoryPostDTO updateCategory(Long id, CategoryPostDTO dto) throws Exception;

	public CategoryPostDTO getCategoryById(Long id) throws Exception;

	public Category getFindById(Long id) throws Exception;

}
