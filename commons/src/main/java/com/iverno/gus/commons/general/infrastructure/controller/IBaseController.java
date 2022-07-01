package com.iverno.gus.commons.general.infrastructure.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface IBaseController  <M, ID> {
	ResponseEntity<?>  getAll();
	
	ResponseEntity<?> get(ID id);
	
	ResponseEntity<?> save(M modelBO);

	ResponseEntity<?> update(M modelBO);
	
	ResponseEntity<?> delete(ID id);
	
	
	
	
}