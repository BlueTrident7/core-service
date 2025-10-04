package com.bluetrident.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bluetrident.entity.PaymentTransaction;

public interface IPaymentTransactionRepository extends JpaRepository<PaymentTransaction, Long> {
	Optional<PaymentTransaction> findByTransactionId(String transactionId);

	List<PaymentTransaction> findByInvestmentId(Long investmentId);
}
