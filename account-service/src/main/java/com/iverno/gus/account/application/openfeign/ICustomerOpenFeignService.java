package com.iverno.gus.account.application.openfeign;

import com.iverno.gus.commons.general.application.dto.CustomerDTO;
public interface ICustomerOpenFeignService {
	CustomerDTO getCustomerById(String id);
}
