package com.bluetredint.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bluetredint.dto.InvestmentPlanDTO;
import com.bluetredint.dto.InvestmentPlansDTO;
import com.bluetredint.service.CategoryService;
import com.bluetredint.service.InvestmentPlanService;
import com.bluetrident.config.ApplicationResponse;
import com.bluetrident.config.CommonConstants;
import com.bluetrident.config.exception.ApplicationException;
import com.bluetrident.dto.CategoryPostDTO;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class MasterController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private InvestmentPlanService plansService;

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

	@GetMapping("get/category")
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
	public ApplicationResponse<InvestmentPlansDTO> addPlan(@RequestBody InvestmentPlansDTO dto) throws Exception {
		return new ApplicationResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK.value()),
				CommonConstants.OK, plansService.addPlan(dto));
	}

	@GetMapping("get/plans")
	public ApplicationResponse<List<InvestmentPlansDTO>> getAllPlans() {
		return new ApplicationResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK.value()),
				CommonConstants.OK, plansService.getAllPlans());
	}

	@GetMapping("/plan/{id}")
	public ApplicationResponse<InvestmentPlansDTO> getPlan(@PathVariable Long id) throws Exception {
		try {
			return new ApplicationResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK.value()),
					CommonConstants.OK, plansService.getPlanById(id));
		} catch (ApplicationException e) {
			return new ApplicationResponse<>(CommonConstants.ERROR,
					String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), e.getMessage(), null);
		}
	}

	@PutMapping("/plan/{id}")
	public ApplicationResponse<InvestmentPlansDTO> updatePlan(@PathVariable Long id,
			@RequestBody InvestmentPlansDTO dto) throws Exception {
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

	@GetMapping("investment/plans")
	public ApplicationResponse<List<InvestmentPlanDTO>> getInvestmentPlans() {
		return new ApplicationResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK.value()),
				CommonConstants.OK, plansService.getInvestmentPlans());
	}
}
