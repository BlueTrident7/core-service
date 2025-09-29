package com.bluetredint.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bluetredint.dto.CategoryPostDTO;
import com.bluetredint.entity.Category;
import com.bluetredint.repository.CategoryRepository;
import com.bluetredint.service.CategoryService;

import jakarta.transaction.Transactional;

public class CategoryServiceImpl implements CategoryService {

	@Autowired

	private CategoryRepository repository;

	@Override
	@Transactional
	public CategoryPostDTO addCategory(CategoryPostDTO dto) throws Exception {
		if (repository.findByCategoryName(dto.getCategoryName()).isPresent())
			throw new Exception("Category already exists");

		Category c = new Category();
		c.setCategoryName(dto.getCategoryName());
		c.setDescription(dto.getDescription());
		c.setIdentifierCode("CAT-" + System.currentTimeMillis());
		repository.save(c);

		return dto;
	}

	@Override
	@Transactional
	public CategoryPostDTO updateCategory(Long id, CategoryPostDTO dto) throws Exception {
		Category c = repository.findById(id).orElseThrow(() -> new Exception("Category not found"));
		c.setCategoryName(dto.getCategoryName());
		c.setDescription(dto.getDescription());
		repository.save(c);
		return dto;
	}

	@Override
	@Transactional
	public void deleteCategory(Long id) throws Exception {
		Category c = repository.findById(id).orElseThrow(() -> new Exception("Category not found"));
		repository.delete(c);
	}

	@Override
	public List<CategoryPostDTO> getAllCategories() {
		return repository.findAll().stream().map(c -> {
			CategoryPostDTO dto = new CategoryPostDTO();
			dto.setCategoryName(c.getCategoryName());
			dto.setDescription(c.getDescription());
			return dto;
		}).toList();
	}

	public CategoryPostDTO getCategoryById(Long id) throws Exception {
		Category c = repository.findById(id).orElseThrow(() -> new Exception("Category not found"));
		CategoryPostDTO dto = new CategoryPostDTO();
		dto.setCategoryName(c.getCategoryName());
		dto.setDescription(c.getDescription());
		return dto;
	}

	@Override
	public Category getFindById(Long id) throws Exception {
		Category c = repository.findById(id).orElseThrow(() -> new Exception("Category not found"));
		return c;
	}
}
