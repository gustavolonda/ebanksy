package com.iverno.gus.commons.general.infrastructure.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface IBaseController  <S, ID> {
	ResponseEntity<?>  getAll();
	
	ResponseEntity<?> get(ID id);
	
	ResponseEntity<?> save(S request);

	ResponseEntity<?> update(S request);
	
	ResponseEntity<?> delete(ID id);
	
	
	
	
}