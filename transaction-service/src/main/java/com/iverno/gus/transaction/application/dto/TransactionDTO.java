package com.iverno.gus.transaction.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {
	private String id;
	private String transactionDate;
	private String customerName;
	private String accountNum;
	private String accountType;
	private String transactionType;
	private double initialBalance;
	private boolean status;
	private double value;
	private double availableBalance;
}
