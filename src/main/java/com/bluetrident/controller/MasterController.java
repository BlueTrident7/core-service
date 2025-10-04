package com.bluetrident.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bluetrident.config.ApplicationResponse;
import com.bluetrident.config.CommonConstants;
import com.bluetrident.config.exception.ApplicationException;
import com.bluetrident.dto.AdminPanelDTO;
import com.bluetrident.dto.CategoryGetDTO;
import com.bluetrident.dto.CategoryPostDTO;
import com.bluetrident.dto.InvestmentGETDTO;
import com.bluetrident.dto.InvestmentPlanDTO;
import com.bluetrident.dto.InvestmentPlansDTO;
import com.bluetrident.dto.TransactionsDTO;
import com.bluetrident.service.AdminPanelService;
import com.bluetrident.service.CategoryService;
import com.bluetrident.service.InvestmentPlanService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
//@RequestMapping("/api-gateway/") // <--- add this

public class MasterController {

	private final CategoryService categoryService;
	private final InvestmentPlanService plansService;
	private final AdminPanelService adminPanelService;

	@PostMapping("add/category")
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
	public ApplicationResponse<List<CategoryGetDTO>> getAllCategories() {
		return new ApplicationResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK.value()),
				CommonConstants.OK, categoryService.getAllCategories());
	}

	@GetMapping("/category/{id}")
	public ApplicationResponse<CategoryGetDTO> getCategory(@PathVariable Long id) throws Exception {
		try {
			return new ApplicationResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK.value()),
					CommonConstants.OK, categoryService.getCategoryById(id));
		} catch (ApplicationException e) {
			return new ApplicationResponse<>(CommonConstants.ERROR,
					String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), e.getMessage(), null);
		}
	}

	@PutMapping("update/category/{id}")
	public ApplicationResponse<CategoryPostDTO> updateCategory(@PathVariable("id") Long id,
			@RequestBody CategoryPostDTO dto) throws Exception {
		try {
			return new ApplicationResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK.value()),
					CommonConstants.OK, categoryService.updateCategory(id, dto));
		} catch (ApplicationException e) {
			return new ApplicationResponse<>(CommonConstants.ERROR,
					String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), e.getMessage(), null);
		}
	}

	@DeleteMapping("delete/category/{id}")
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

	@PostMapping("add/plan")
	public ApplicationResponse<InvestmentPlansDTO> addPlan(@RequestBody InvestmentPlansDTO dto) throws Exception {
		return new ApplicationResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK.value()),
				CommonConstants.OK, plansService.addPlan(dto));
	}

	@GetMapping("get/plans")
	public ApplicationResponse<List<InvestmentGETDTO>> getAllPlans() {
		return new ApplicationResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK.value()),
				CommonConstants.OK, plansService.getAllPlans());
	}

	@GetMapping("/plan/{id}")
	public ApplicationResponse<InvestmentGETDTO> getPlan(@PathVariable Long id) throws Exception {
		try {
			return new ApplicationResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK.value()),
					CommonConstants.OK, plansService.getPlanById(id));
		} catch (ApplicationException e) {
			return new ApplicationResponse<>(CommonConstants.ERROR,
					String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), e.getMessage(), null);
		}
	}

	@PutMapping("update/plan/{id}")
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

	@DeleteMapping("delete/plan/{id}")
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

	@GetMapping("transaction/list")
	public ApplicationResponse<List<TransactionsDTO>> getTransactionList(
			@RequestParam(name = "userId", required = true) Long userId) {
		return new ApplicationResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK.value()),
				CommonConstants.OK, plansService.getTransactionList(userId));
	}

	@GetMapping("admin/panel")
	public ApplicationResponse<AdminPanelDTO> getAdminOverViewDetails(
			@RequestParam(name = "adminId", required = true) Long adminId) {
		return new ApplicationResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK.value()),
				CommonConstants.OK, adminPanelService.getAdminOverViewDetails(adminId));
	}
}