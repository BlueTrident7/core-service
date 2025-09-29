package com.bluetredint.serviceimpl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

import com.bluetredint.dto.InvestmentRequest;
import com.bluetredint.dto.InvestmentResponse;
import com.bluetredint.entity.PaymentTransaction;
import com.bluetredint.entity.UserInvestmentPlans;
import com.bluetredint.enums.InvestmentStatus;
import com.bluetredint.enums.PaymentStatus;
import com.bluetredint.repository.InvestmentPlansRepository;
import com.bluetredint.repository.PaymentTransactionRepository;
import com.bluetredint.repository.UserInvestmentRepository;
import com.bluetredint.repository.UserRepository;
import com.bluetredint.service.InvestmentService;

import jakarta.transaction.Transactional;

public class InvestmentServiceImpl implements InvestmentService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private InvestmentPlansRepository planRepository;

	@Autowired
	private UserInvestmentRepository investmentRepository;

	@Autowired
	private PaymentTransactionRepository transactionRepository;

	@Transactional
	public InvestmentResponse initiateInvestment(InvestmentRequest request) {
		var user = userRepository.findById(request.getUserId())
				.orElseThrow(() -> new RuntimeException("User not found"));

		var plan = planRepository.findById(request.getPlanId())
				.orElseThrow(() -> new RuntimeException("Plan not found"));

		if (request.getInvestedAmount() < plan.getPrice()) {
			throw new RuntimeException("Amount must be between " + plan.getPrice());
		}

		var investment = UserInvestmentPlans.builder().user(user).plan(plan).status(InvestmentStatus.PENDING).build();

		investmentRepository.save(investment);

		var transaction = PaymentTransaction.builder().investment(investment).amount(request.getInvestedAmount())
				.status(PaymentStatus.INITIATED).transactionId("TXN-" + System.currentTimeMillis())
				.createdAt(LocalDateTime.now()).build();

		transactionRepository.save(transaction);

		return new InvestmentResponse(investment.getId(), user.getFullName(), plan.getPlanName(), plan.getPrice(),
				investment.getStatus().toString());
	}
}
