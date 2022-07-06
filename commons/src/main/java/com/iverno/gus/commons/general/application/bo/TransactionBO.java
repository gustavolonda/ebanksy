package com.iverno.gus.commons.general.application.bo;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionBO {
	private String id;
	private AccountBO accountBO;
	private String transactionDate;
	private String transactionType;
	private double value;
	private double availableBalance;
	private boolean status;

}
