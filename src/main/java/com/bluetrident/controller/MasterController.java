package com.bluetrident.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bluetrident.config.ApplicationResponse;
import com.bluetrident.config.CommonConstants;
import com.bluetrident.config.exception.ApplicationException;
import com.bluetrident.dto.CategoryPostDTO;
import com.bluetrident.dto.PlansDTO;
import com.bluetrident.service.CategoryService;
import com.bluetrident.service.PlanService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class MasterController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private PlanService plansService;

	@PostMapping("/category")
	public ApplicationResponse<CategoryPostDTO> addCategory(@RequestBody CategoryPostDTO dto) throws Exception {
		try {
			return new ApplicationResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK.value()),
					CommonConstants.OK, categoryService.addCategory(dto));
		} catch (ApplicationException e) {
			return new ApplicationResponse<>(CommonConstants.ERROR,
					String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), e.getMessage(), null);
		}
	}

	@GetMapping("/category")
	public ApplicationResponse<List<CategoryPostDTO>> getAllCategories() {
		return new ApplicationResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK.value()),
				CommonConstants.OK, categoryService.getAllCategories());
	}

	@GetMapping("/category/{id}")
	public ApplicationResponse<CategoryPostDTO> getCategory(@PathVariable Long id) throws Exception {
		try {
			return new ApplicationResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK.value()),
					CommonConstants.OK, categoryService.getCategoryById(id));
		} catch (ApplicationException e) {
			return new ApplicationResponse<>(CommonConstants.ERROR,
					String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), e.getMessage(), null);
		}
	}

	@PutMapping("/category/{id}")
	public ApplicationResponse<CategoryPostDTO> updateCategory(@PathVariable Long id, @RequestBody CategoryPostDTO dto)
			throws Exception {
		try {
			return new ApplicationResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK.value()),
					CommonConstants.OK, categoryService.updateCategory(id, dto));
		} catch (ApplicationException e) {
			return new ApplicationResponse<>(CommonConstants.ERROR,
					String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), e.getMessage(), null);
		}
	}

	@DeleteMapping("/category/{id}")
	public ApplicationResponse<Void> deleteCategory(@PathVariable Long id) throws Exception {
		try {
			categoryService.deleteCategory(id);
			return new ApplicationResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK.value()),
					"Category deleted", null);
		} catch (ApplicationException e) {
			return new ApplicationResponse<>(CommonConstants.ERROR,
					String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), e.getMessage(), null);
		}
	}

	// ================= Plans =================
	@PostMapping("/plan")
	public ApplicationResponse<PlansDTO> addPlan(@RequestBody PlansDTO dto) throws Exception {
		return new ApplicationResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK.value()),
				CommonConstants.OK, plansService.addPlan(dto));
	}

	@GetMapping("/plan")
	public ApplicationResponse<List<PlansDTO>> getAllPlans() {
		return new ApplicationResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK.value()),
				CommonConstants.OK, plansService.getAllPlans());
	}

	@GetMapping("/plan/{id}")
	public ApplicationResponse<PlansDTO> getPlan(@PathVariable Long id) throws Exception {
		try {
			return new ApplicationResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK.value()),
					CommonConstants.OK, plansService.getPlanById(id));
		} catch (ApplicationException e) {
			return new ApplicationResponse<>(CommonConstants.ERROR,
					String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), e.getMessage(), null);
		}
	}

	@PutMapping("/plan/{id}")
	public ApplicationResponse<PlansDTO> updatePlan(@PathVariable Long id, @RequestBody PlansDTO dto) throws Exception {
		try {
			return new ApplicationResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK.value()),
					CommonConstants.OK, plansService.updatePlan(id, dto));
		} catch (ApplicationException e) {
			return new ApplicationResponse<>(CommonConstants.ERROR,
					String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), e.getMessage(), null);
		}
	}

	@DeleteMapping("/plan/{id}")
	public ApplicationResponse<Void> deletePlan(@PathVariable Long id) throws Exception {
		try {
			plansService.deletePlan(id);
			return new ApplicationResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK.value()),
					"Plan deleted", null);
		} catch (ApplicationException e) {
			return new ApplicationResponse<>(CommonConstants.ERROR,
					String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), e.getMessage(), null);
		}
	}
}
