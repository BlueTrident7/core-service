package com.bluetrident.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluetrident.dto.InvestmentRequest;
import com.bluetrident.dto.InvestmentResponse;
import com.bluetrident.entity.PaymentTransaction;
import com.bluetrident.entity.UserInvestmentPlans;
import com.bluetrident.enums.InvestmentStatus;
import com.bluetrident.enums.PaymentStatus;
import com.bluetrident.repository.InvestmentPlansRepository;
import com.bluetrident.repository.IPaymentTransactionRepository;
import com.bluetrident.repository.IUserInvestmentRepository;
import com.bluetrident.repository.IUserRepository;
import com.bluetrident.service.InvestmentService;

@Service
public class InvestmentServiceImpl implements InvestmentService {

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private InvestmentPlansRepository planRepository;

	@Autowired
	private IUserInvestmentRepository investmentRepository;

	@Autowired
	private IPaymentTransactionRepository transactionRepository;

	public InvestmentResponse initiateInvestment(InvestmentRequest request) {
		var user = userRepository.findById(request.getUserId())
				.orElseThrow(() -> new RuntimeException("User not found"));
		var plan = planRepository.findById(request.getPlanId())
				.orElseThrow(() -> new RuntimeException("Plan not found"));

		if (request.getInvestedAmount() < plan.getPrice()) {
			throw new RuntimeException("Amount must be between plan min and max");
		}

		var investment = UserInvestmentPlans.builder().user(user).plan(plan).status(InvestmentStatus.PENDING).build();
		investmentRepository.save(investment);

		var transaction = PaymentTransaction.builder().amount(request.getInvestedAmount())
				.status(PaymentStatus.INITIATED).createdAt(java.time.LocalDateTime.now()).build();
		transactionRepository.save(transaction);

		return new InvestmentResponse(investment.getId(), user.getFullName(), plan.getPlanName(), plan.getPrice(),
				investment.getStatus().toString());
	}
}
