package com.iverno.gus.transaction.application.adapter;

import com.iverno.gus.commons.general.application.bo.AccountBO;
import com.iverno.gus.commons.general.application.bo.TransactionBO;
import com.iverno.gus.commons.general.application.bo.TransactionTypeDomain;
import com.iverno.gus.commons.general.application.dto.AccountDTO;
import com.iverno.gus.transaction.application.dto.TransactionDTO;
import com.iverno.gus.transaction.domain.entity.TransactionEntity;
import static com.iverno.gus.commons.general.util.Util.dateToString;

public class TransactionAdapter {
	public static TransactionEntity transactionBOToTransactionEntity(TransactionBO transactionBO) {
		TransactionEntity transactionEntity = new TransactionEntity().builder()
														.id(transactionBO.getId())
														.accountId(transactionBO.getAccountBO() != null ? transactionBO.getAccountBO().getId():"")
														.transactionType(transactionBO.getTransactionType())
														.value(transactionBO.getValue())
														.availableBalance(transactionBO.getAvailableBalance())
														.build();
		transactionEntity.setStatus(transactionBO.isStatus());
		return transactionEntity;
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

	public static TransactionDTO transactionEntityToTransactionDTO(TransactionEntity entity) {
		return new TransactionDTO().builder()
								.id(entity.getId())
								.transactionDate(entity.getCreateDate() != null ? dateToString(entity.getCreateDate()):"")
								.customerName("")
								.accountNum("")
								.accountType("")
								.transactionType(entity.getTransactionType())
								.initialBalance(0)
								.value(entity.getValue())
								.status(entity.isStatus())
								.availableBalance(entity.getAvailableBalance())
								.build();
		
		
	}
	
	public static TransactionDTO transactionEntityToTransactionDTO(TransactionEntity entity, AccountDTO accountDTO) {
		return new TransactionDTO().builder()
								.id(entity.getId())
								.transactionDate(entity.getCreateDate() != null ? dateToString(entity.getCreateDate()):"")
								.customerName(accountDTO.getCustomerName())
								.accountNum(accountDTO.getAccountNum())
								.accountType(accountDTO.getAccountType())
								.transactionType(entity.getTransactionType())
								.initialBalance(accountDTO.getInitialBalance())
								.value(entity.getValue())
								.status(entity.isStatus())
								.availableBalance(entity.getAvailableBalance())
								.build();
		
		
	}
}
