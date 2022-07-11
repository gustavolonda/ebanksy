package com.iverno.gus.transaction.infrastructure.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.iverno.gus.commons.general.config.Constants.*;

import com.iverno.gus.commons.general.application.bo.TransactionBO;
import com.iverno.gus.commons.general.application.service.IEndPointService;
import com.iverno.gus.commons.general.infrastructure.controller.BaseControllerImpl;
import com.iverno.gus.transaction.application.service.TransactionService;
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping(REQUEST_MAPPING_TRANACTIONS)
public class TransactionController extends BaseControllerImpl<TransactionBO, String> {
	@Autowired
	public TransactionController(@Qualifier("transactionService") IEndPointService iEndPointService) {
		this.iEndPointService = iEndPointService;
		
	}
	@GetMapping("/getListActive")
	public ResponseEntity<?> getListActive() {
		TransactionService transactionService = (TransactionService) iEndPointService;
		return ResponseEntity.ok().body(transactionService.getListActive());
	}
	@CrossOrigin("http://localhost:3000")
	@GetMapping("/searchText/{searchText}")
	public ResponseEntity<?> getBySearchText(@PathVariable String searchText) {
		TransactionService transactionService = (TransactionService) iEndPointService;
		return ResponseEntity.ok().body(transactionService.getBySearchText(searchText));
	}
}
