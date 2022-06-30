package com.iverno.gus.commons.general.infrastructure.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.iverno.gus.commons.general.application.service.IEndPointService;
@Component
public class BaseControllerImpl <S, ID> implements  IBaseController   <S, ID>{
	protected IEndPointService iEndPointService;
	@GetMapping
	@Override
	public ResponseEntity<?> getAll() {

		return ResponseEntity.ok().body(iEndPointService.getAll());
	}

	@GetMapping("/{id}")
	@Override
	public ResponseEntity<?> get(@PathVariable ID id) {
		return ResponseEntity.ok().body(iEndPointService.get(id));
	}

	@PostMapping
	@Override
	public ResponseEntity<?> save(@RequestBody S request) {
		return ResponseEntity.ok().body(iEndPointService.save(request));
	}

	@PutMapping
	@Override
	public ResponseEntity<?> update(@RequestBody S request) {
		return ResponseEntity.ok().body(iEndPointService.save(request));
	}

	@DeleteMapping("/{id}")
	@Override
	public ResponseEntity<?> delete(@PathVariable ID id) {
		return ResponseEntity.ok().body( iEndPointService.delete(id));
	}

}
