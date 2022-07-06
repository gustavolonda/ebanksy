package com.iverno.gus.account.application.openfeign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static com.iverno.gus.account.config.Constants.*;

import com.iverno.gus.commons.general.config.FeignResponseDecoderConfig;
import com.iverno.gus.commons.general.domain.model.ResponseBase;

@FeignClient(name=USER_SERVICE,configuration= FeignResponseDecoderConfig.class)
public interface ICustomerClient {
	@RequestMapping(method = RequestMethod.GET, value = GET_CUSTOMER_BY_ID)
	ResponseBase getCustomerById(@PathVariable("id") String id);

}