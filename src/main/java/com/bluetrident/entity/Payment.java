package com.bluetrident.entity;

import com.bluetrident.enums.PaymentStatus;
import com.bluetrident.enums.TransactionType;

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
@Table(name = "payments")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String orderId;

	private String razorPayOrderId; // Razorpay order_id

	private String razorPayPaymentId; // Razorpay payment_id

	private String razorPaySignature; // Razorpay signature verification

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne
	@JoinColumn(name = "investment_id", nullable = false)
	private UserInvestmentPlans investment;

	private long amount; // Amount in smallest currency unit (e.g., paise)

	private String currency; // e.g., INR

	private String paymentMethod; // Card, UPI, NetBanking, Wallet etc.

	@Enumerated(EnumType.STRING)
	private PaymentStatus status; // CREATED, SUCCESS, FAILED, REFUNDED

	private String errorCode; // if failed

	private String errorDescription;
	
	@Enumerated(EnumType.STRING)
	private TransactionType type;

}
