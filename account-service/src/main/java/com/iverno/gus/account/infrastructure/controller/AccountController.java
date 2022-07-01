package com.iverno.gus.account.infrastructure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iverno.gus.commons.general.application.bo.AccountBO;
import com.iverno.gus.commons.general.application.service.IEndPointService;
import com.iverno.gus.commons.general.infrastructure.controller.BaseControllerImpl;

@RestController
@RequestMapping("accounts")
public class AccountController extends BaseControllerImpl<AccountBO, String> {
	@Autowired
	public AccountController(@Qualifier("accountService") IEndPointService iEndPointService) {
		this.iEndPointService = iEndPointService;
		
	}
}
