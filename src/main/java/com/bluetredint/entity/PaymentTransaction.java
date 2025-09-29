package com.bluetredint.entity;

import java.time.LocalDateTime;

import com.bluetredint.enums.PaymentStatus;

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
	@JoinColumn(name = "investment_id")
	private UserInvestmentPlans investment;

	private String transactionId; // from gateway (e.g., Razorpay/Stripe/PayPal)

	private Double amount;

	@Enumerated(EnumType.STRING)
	private PaymentStatus status; // INITIATED, SUCCESS, FAILED

	private String paymentMethod; // e.g., CARD, UPI, NETBANKING
	private LocalDateTime createdAt;
}
