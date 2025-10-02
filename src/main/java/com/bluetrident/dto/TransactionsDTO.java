package com.bluetrident.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionsDTO {

	private Long id;
	private String type;
	private String transactionNumber;
	private LocalDate date;
	private Double amount;
	private String status;

}
