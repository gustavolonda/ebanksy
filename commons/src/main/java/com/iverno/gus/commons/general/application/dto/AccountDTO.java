package com.iverno.gus.commons.general.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
	private String id;
	private String accountNum;
	private String accountType;
	private double initialBalance;
	private boolean status;
	private String customerName;
	private double availableBalance;
	private double dailyWithdrawalLimit;

}
