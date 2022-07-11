package com.iverno.gus.userservice.customer.infrastructure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.iverno.gus.commons.general.config.Constants.*;
import com.iverno.gus.commons.general.application.bo.CustomerBO;
import com.iverno.gus.commons.general.application.service.IEndPointService;
import com.iverno.gus.commons.general.infrastructure.controller.BaseControllerImpl;
import com.iverno.gus.userservice.customer.application.service.CustomerService;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(REQUEST_MAPPING_CUSTOMERS)
public class CustomerController extends BaseControllerImpl<CustomerBO, String>{ 
	CustomerService customerService;
	
	@Autowired
	public CustomerController(@Qualifier("customerService") IEndPointService iEndPointService) {
		this.iEndPointService = iEndPointService;
		customerService = (CustomerService) this.iEndPointService;
	}
	@CrossOrigin("http://localhost:3000")
	@GetMapping("/searchText/{searchText}")
	public ResponseEntity<?> getBySearchText(@PathVariable String searchText) {
		return ResponseEntity.ok().body(customerService.getByFilterSearchText(searchText));
	}
	
	@CrossOrigin("http://localhost:3000")
	@GetMapping("/getTopTeen")
	public ResponseEntity<?> getTopTeen() {
		return ResponseEntity.ok().body(customerService.getTopTeen());
	}
	@CrossOrigin("http://localhost:3000")
	@GetMapping("/getListActive")
	public ResponseEntity<?> getListActive() {
		return ResponseEntity.ok().body(customerService.findAllByActive());
	}
	
	

}
