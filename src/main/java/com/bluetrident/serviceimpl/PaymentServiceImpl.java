package com.bluetrident.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluetrident.dto.PaymentCallbackRequest;
import com.bluetrident.enums.InvestmentStatus;
import com.bluetrident.enums.PaymentStatus;
import com.bluetrident.repository.IPaymentTransactionRepository;
import com.bluetrident.repository.IUserInvestmentRepository;
import com.bluetrident.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private IPaymentTransactionRepository transactionRepository;

	@Autowired
	private IUserInvestmentRepository investmentRepository;

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
