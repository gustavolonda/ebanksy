package com.iverno.gus.account.application.adapter;

import com.iverno.gus.account.domain.entities.AccountEntity;
import com.iverno.gus.commons.general.application.bo.AccountBO;
import com.iverno.gus.commons.general.application.bo.CustomerBO;
import com.iverno.gus.commons.general.application.dto.AccountDTO;

public class AccountAdapter {

	public static AccountEntity accountBOToAccountEntity(AccountBO accountBO) {	
		AccountEntity accountEntity = new AccountEntity().builder()
								.id(accountBO.getId())
								.customerId(accountBO.getCustomerBO() != null ? accountBO.getCustomerBO().getId():"")
								.accountNum(accountBO.getAccountNum())
								.accountType(accountBO.getAccountType())
								.initialBalance(accountBO.getInitialBalance())
								.availableBalance(accountBO.getAvailableBalance())
							.build();
		accountEntity.setStatus(accountBO.isStatus());
		return accountEntity;
	}

	public static AccountBO accountentityToAccountBO(AccountEntity entity) {
		return new AccountBO().builder()
							.id(entity.getId())
							.customerBO(new CustomerBO().builder()
														.id(entity.getCustomerId() != null ? entity.getCustomerId():"")
														.build())
							.accountNum(entity.getAccountNum())
							.accountType(entity.getAccountType())
							.initialBalance(entity.getInitialBalance())
							.status(entity.isStatus())
							.availableBalance(entity.getAvailableBalance())
							.build();
	}
	public static AccountDTO accountEntityToAccountDTO(AccountEntity entity) {
		return new AccountDTO().builder()
							.id(entity.getId())
							.accountNum(entity.getAccountNum())
							.accountType(entity.getAccountType())
							.initialBalance(entity.getInitialBalance())
							.status(entity.isStatus())
							.customerName("")
							.availableBalance(entity.getAvailableBalance())
							.build();
	}
}
