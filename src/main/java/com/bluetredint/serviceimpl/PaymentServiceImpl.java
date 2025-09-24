package com.bluetredint.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.bluetredint.dto.PaymentCallbackRequest;
import com.bluetredint.enums.InvestmentStatus;
import com.bluetredint.enums.PaymentStatus;
import com.bluetredint.repository.PaymentTransactionRepository;
import com.bluetredint.repository.UserInvestmentRepository;
import com.bluetredint.service.PaymentService;

public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentTransactionRepository transactionRepository;

	@Autowired
	private UserInvestmentRepository investmentRepository;

	public String updatePaymentStatus(PaymentCallbackRequest request) {
		var transaction = transactionRepository.findByTransactionId(request.getTransactionId())
				.orElseThrow(() -> new RuntimeException("Transaction not found"));

		transaction.setStatus(request.getStatus());
		transactionRepository.save(transaction);

		var investment = transaction.getInvestment();
		if (request.getStatus().equals(PaymentStatus.SUCCESS)) {
			investment.setStatus(InvestmentStatus.ACTIVE);
		} else {
			investment.setStatus(InvestmentStatus.FAILED);
		}
		investmentRepository.save(investment);

		return "Payment status updated: " + request.getStatus();
	}
}
