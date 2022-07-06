package com.iverno.gus.account.application.validate;

import static com.iverno.gus.commons.general.domain.model.ResponseBaseStatusDomain.ERROR;

import org.springframework.stereotype.Component;

import static com.iverno.gus.account.config.Constants.MODULE_ACCOUNT;

import com.iverno.gus.account.domain.entities.AccountEntity;
import com.iverno.gus.commons.general.application.exception.BaseException;
@Component
public class AccountValidate {
	public BaseException validateBeforeSave(AccountEntity entity) {

		return null;
	}
}
