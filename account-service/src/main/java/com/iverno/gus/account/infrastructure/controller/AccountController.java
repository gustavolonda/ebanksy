package com.iverno.gus.account.infrastructure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iverno.gus.account.application.service.AccountService;
import com.iverno.gus.commons.general.application.bo.AccountBO;
import com.iverno.gus.commons.general.application.service.IEndPointService;
import com.iverno.gus.commons.general.infrastructure.controller.BaseControllerImpl;
import static com.iverno.gus.commons.general.config.Constants.*;

@RestController
@RequestMapping(REQUEST_MAPPING_ACCOUNTS)
public class AccountController extends BaseControllerImpl<AccountBO, String> {
	@Autowired
	public AccountController(@Qualifier("accountService") IEndPointService iEndPointService) {
		this.iEndPointService = iEndPointService;
		
	}
	@PutMapping("/{id}/{availableBalanceNew}")
	public ResponseEntity<?> availableBalanceUpdate(@PathVariable String id,
													@PathVariable double availableBalanceNew) {
		AccountService accountService = (AccountService) iEndPointService;
		return ResponseEntity.ok().body(iEndPointService.toResponseBase(accountService.availableBalanceUpdate(id, availableBalanceNew)));
	}
}
