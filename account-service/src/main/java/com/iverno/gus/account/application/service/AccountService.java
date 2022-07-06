package com.iverno.gus.account.application.service;

import static com.iverno.gus.account.config.Constants.MODULE_ACCOUNT;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.iverno.gus.account.application.adapter.AccountAdapter;
import com.iverno.gus.account.application.openfeign.CustomerOpenFeignServiceImpl;
import com.iverno.gus.account.application.validate.AccountValidate;
import com.iverno.gus.account.domain.entities.AccountEntity;
import com.iverno.gus.account.domain.repository.AccountRepository;
import com.iverno.gus.commons.general.application.bo.AccountBO;
import com.iverno.gus.commons.general.application.dto.AccountDTO;
import com.iverno.gus.commons.general.application.dto.CustomerDTO;
import com.iverno.gus.commons.general.application.exception.BaseException;
import com.iverno.gus.commons.general.application.service.EndPointServiceImpl;
import com.iverno.gus.commons.general.util.Util;

import lombok.SneakyThrows;
@Service
@Qualifier("accountService")
public class AccountService extends EndPointServiceImpl<AccountDTO, AccountBO, AccountEntity, String> {
	private String className = this.getClass().getSimpleName();
	@Autowired
	AccountRepository repository;
	@Autowired
	CustomerOpenFeignServiceImpl customerOpenFeignServiceImpl;
	@Autowired
	AccountValidate accountValidate;
	@Override
	public JpaRepository<AccountEntity, String> getDao() {
		return repository;
	}

	@Override
	public AccountEntity runCreate(AccountEntity entity) {
		String acccountNum = "";
		boolean checkAccounNum = true;
		while (checkAccounNum) {
			acccountNum = Util.accountNumGenerate();
			List<AccountEntity> accountlist = repository.findByAccountNum(acccountNum);
			if (accountlist != null) {
				if (accountlist.size() == 0)
					checkAccounNum = false;
			} else
				checkAccounNum = false;

		}
		entity.setAccountNum(acccountNum);
		entity.setAvailableBalance(entity.getInitialBalance());
		return entity;
	}

	@SneakyThrows
	@Override
	public AccountEntity runUpdate(AccountEntity entity) {
		try {
			AccountEntity accountEntityResul = this.getById(entity.getId());
			accountEntityResul.setAccountNum(entity.getAccountNum());
			accountEntityResul.setAccountNum(entity.getAccountType());
			accountEntityResul.setInitialBalance(entity.getInitialBalance());
			accountEntityResul.setAvailableBalance(entity.getAvailableBalance());

			return accountEntityResul;
		} catch (Exception e) {

			throw e;
		}
	}

	@Override
	public AccountEntity statusChangeDelete(AccountEntity entity) {
		entity.setStatus(false);
		return entity;
	}

	@Override
	public AccountEntity modelBOToEntity(Object modelBO) {
		return AccountAdapter.accountBOToAccountEntity((AccountBO) modelBO);
	}

	@SneakyThrows
	@Override
	public AccountDTO entityToModelDTO(AccountEntity entity) {
		try {
			AccountDTO accountDTO = AccountAdapter.accountEntityToAccountDTO(entity);
			if (entity.getCustomerId() != null && !entity.getCustomerId().isEmpty()) {
				CustomerDTO customerDTO = customerOpenFeignServiceImpl.getCustomerById(entity.getCustomerId());
				accountDTO.setCustomerName(customerDTO != null ? customerDTO.getName() : "");
			}
			return accountDTO;
		} catch (Exception e) {

			throw e;
		}
	}

	@Override
	public String nameModule() {
		return MODULE_ACCOUNT;
	}

	@Override
	public String className() {
		return className;
	}

	@Override
	public BaseException validateBeforeSave(AccountEntity entity) {
		return accountValidate.validateBeforeSave(entity);
	}
	
	@SneakyThrows
	public AccountEntity availableBalanceUpdate(String accountId, double availableBalanceNew) {
		try {
			AccountEntity accountEntity      = this.getById(accountId);
			accountEntity.setAvailableBalance(availableBalanceNew);
			AccountEntity accountEntityResul = this.update(accountEntity);

			
			return accountEntityResul;
		} catch (Exception e) {

			throw e;
		}
	}


}