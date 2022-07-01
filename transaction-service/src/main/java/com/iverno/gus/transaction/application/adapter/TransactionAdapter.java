package com.iverno.gus.transaction.application.adapter;

import com.iverno.gus.commons.general.application.bo.AccountBO;
import com.iverno.gus.commons.general.application.bo.TransactionBO;
import com.iverno.gus.transaction.domain.entity.TransactionEntity;

public class TransactionAdapter {

	public static TransactionEntity transactionBOToTransactionEntity(TransactionBO transactionBO) {
		return new TransactionEntity().builder()
									.id(transactionBO.getId())
									.accountId(transactionBO.getAccountBO() != null ? transactionBO.getAccountBO().getId():"")
									.transactionType(transactionBO.getTransactionType())
									.value(transactionBO.getValue())
									.availableBalance(transactionBO.getAvailableBalance())
									.build();
	}

	public static TransactionBO transactionEntityToTransactionBO(TransactionEntity entity) {
		
		return new TransactionBO().builder()
								.id(entity.getId())
								.accountBO(new AccountBO().builder()
														.id(entity.getAccountId())
														.build())
								.transactionType(entity.getTransactionType())
								.value(entity.getValue())
								.availableBalance(entity.getAvailableBalance())
								.build();
	}

}
