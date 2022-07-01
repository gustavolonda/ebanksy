package com.iverno.gus.userservice.customer.infrastructure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iverno.gus.commons.general.application.bo.CustomerBO;
import com.iverno.gus.commons.general.application.service.IEndPointService;
import com.iverno.gus.commons.general.infrastructure.controller.BaseControllerImpl;

@RestController
@RequestMapping("customers")
public class CustomerController extends BaseControllerImpl<CustomerBO, String>{ 
	@Autowired
	public CustomerController(@Qualifier("customerService") IEndPointService iEndPointService) {
		this.iEndPointService = iEndPointService;
		
	}

}
