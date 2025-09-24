package com.bluetredint.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bluetredint.entity.PaymentTransaction;

public interface PaymentTransactionRepository extends JpaRepository<PaymentTransaction, Long> {
	Optional<PaymentTransaction> findByTransactionId(String transactionId);

	List<PaymentTransaction> findByInvestmentId(Long investmentId);
}
