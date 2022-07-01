package com.iverno.gus.account.application.service;

import static com.iverno.gus.account.config.Constants.MODULE_ACCOUNT;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.iverno.gus.account.application.adapter.AccountAdapter;
import com.iverno.gus.account.domain.entities.AccountEntity;
import com.iverno.gus.account.domain.repository.AccountRepository;
import com.iverno.gus.commons.general.application.bo.AccountBO;
import com.iverno.gus.commons.general.application.service.EndPointServiceImpl;
import com.iverno.gus.commons.general.util.StringUtil;
@Service
@Qualifier("accountService")
public class AccountService  extends EndPointServiceImpl< AccountBO , AccountEntity, String>{
	private  String className = this.getClass().getSimpleName(); 
	@Autowired
	AccountRepository repository;
	@Override
	public JpaRepository<AccountEntity, String> getDao() {
		return repository;
	}

	@Override
	public AccountEntity runCreate(AccountEntity entity) {
		String acccountNum = "";
		boolean checkAccounNum = true;
		while(checkAccounNum) {
			acccountNum = StringUtil.accountNumGenerate();
			List<AccountEntity> accountlist = repository.findByAccountNum(acccountNum);
			if(accountlist != null) {
				if(accountlist.size() == 0) 
					checkAccounNum = false;
			}else
				checkAccounNum = false;
				
				
			
			
		}
		entity.setAccountNum(acccountNum);
		return entity;
	}

	@Override
	public AccountEntity runUpdate(AccountEntity entity) {
		return entity;
	}

	@Override
	public AccountEntity statusChangeDelete(AccountEntity entity) {
		entity.setStatus(false);
		return entity;
	}

	@Override
	public AccountEntity modelBOToEntity(AccountBO modelBO) {
		return AccountAdapter.accountBOToAccountEntity(modelBO);
	}

	@Override
	public AccountBO entityToModelBO(AccountEntity entity) {
	
		return AccountAdapter.accountentityToAccountBO(entity);
	}

	@Override
	public String nameModule() {
		return MODULE_ACCOUNT;
	}

	@Override
	public String className() {
		return className;
	}



}
