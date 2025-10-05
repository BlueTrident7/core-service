package com.bluetrident.entity;

import java.time.LocalDateTime;

import com.bluetrident.enums.PaymentStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payment_transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "payment_id", nullable = false)
	private Payment payment; // Linked to Payment entity

	private String transactionId; // Razorpay or internal transaction ID

	private Double amount;

	@Enumerated(EnumType.STRING)
	private PaymentStatus status; // INITIATED, SUCCESS, FAILED, REFUNDED

	private String paymentMethod;

	private String gatewayResponse; // optional JSON from Razorpay

	private String remarks; // custom log messages

	private LocalDateTime createdAt;
}
