package com.bluetrident.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluetrident.dto.CategoryGetDTO;
import com.bluetrident.dto.CategoryPostDTO;
import com.bluetrident.entity.Category;
import com.bluetrident.repository.ICategoryRepository;
import com.bluetrident.service.CategoryService;

import jakarta.transaction.Transactional;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired

	private ICategoryRepository repository;

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
	public List<CategoryGetDTO> getAllCategories() {
		return repository.findAll().stream().map(c -> {
			CategoryGetDTO dto = new CategoryGetDTO();
			dto.setId(c.getId());
			dto.setCategoryName(c.getCategoryName());
			dto.setDescription(c.getDescription());
			return dto;
		}).toList();
	}

	public CategoryGetDTO getCategoryById(Long id) throws Exception {
		Category c = repository.findById(id).orElseThrow(() -> new Exception("Category not found"));
		CategoryGetDTO dto = new CategoryGetDTO();
		dto.setId(c.getId());
		dto.setCategoryName(c.getCategoryName());
		dto.setDescription(c.getDescription());
		return dto;
	}

	@Override
	public Category getFindById(Long id) throws Exception {
		return repository.findById(id).get();
	}
}
