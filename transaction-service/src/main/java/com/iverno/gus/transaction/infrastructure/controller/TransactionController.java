package com.iverno.gus.transaction.infrastructure.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.iverno.gus.commons.general.config.Constants.*;
import com.iverno.gus.commons.general.application.bo.TransactionBO;
import com.iverno.gus.commons.general.application.service.IEndPointService;
import com.iverno.gus.commons.general.infrastructure.controller.BaseControllerImpl;

@RestController
@RequestMapping(REQUEST_MAPPING_TRANACTIONS)
public class TransactionController extends BaseControllerImpl<TransactionBO, String> {
	@Autowired
	public TransactionController(@Qualifier("transactionService") IEndPointService iEndPointService) {
		this.iEndPointService = iEndPointService;
		
	}
}
