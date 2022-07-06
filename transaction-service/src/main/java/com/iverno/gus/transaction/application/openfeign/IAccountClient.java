package com.iverno.gus.transaction.application.openfeign;
import static com.iverno.gus.config.Constants.*;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.iverno.gus.commons.general.config.*;
import com.iverno.gus.commons.general.domain.model.ResponseBase;

@FeignClient(name=ACCOUNT_SERVICE,configuration= FeignResponseDecoderConfig.class)
public interface IAccountClient {
	@RequestMapping(method = RequestMethod.GET, value = GET_ACCOUNT_BY_ID)
	ResponseBase getAccountById(@PathVariable("id") String id);
	
	@RequestMapping(method = RequestMethod.PUT, value = AVAILABLE_BALANCE_UPDATE)
	ResponseBase availableBalanceUpdate(@PathVariable String id, @PathVariable double availableBalanceNew) ;
	
}