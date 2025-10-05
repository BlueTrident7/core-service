package com.bluetrident.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bluetrident.entity.Payment;

@Repository
public interface IPaymentRepository extends JpaRepository<Payment, Long> {

	Payment findByRazorPayOrderId(String razorPayOrderId);
}