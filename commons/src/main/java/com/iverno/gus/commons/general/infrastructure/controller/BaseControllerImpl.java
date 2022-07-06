package com.iverno.gus.commons.general.infrastructure.controller;

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
public class BaseControllerImpl <M, ID> implements  IBaseController   <M, ID>{
	protected IEndPointService iEndPointService;
	@GetMapping
	@Override
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok().body(iEndPointService.toResponseBase(iEndPointService.getAll()));
	}

	@GetMapping("/{id}")
	@Override
	public ResponseEntity<?> get(@PathVariable ID id) {
		return ResponseEntity.ok().body(iEndPointService.toResponseBase(iEndPointService.getById(id)));
	}

	@PostMapping
	@Override
	public ResponseEntity<?> save(@RequestBody M modelBO) {
		return ResponseEntity.ok().body(iEndPointService.toResponseBase(iEndPointService.save(iEndPointService.modelBOToEntity(modelBO))));
	}

	@PutMapping
	@Override
	public ResponseEntity<?> update(@RequestBody M modelBO) {
		return ResponseEntity.ok().body(iEndPointService.toResponseBase(iEndPointService.update(iEndPointService.modelBOToEntity(modelBO))));
	}

	@DeleteMapping("/{id}")
	@Override
	public ResponseEntity<?> delete(@PathVariable ID id) {
		return ResponseEntity.ok().body( iEndPointService.toResponseBase(iEndPointService.delete(id)));
	}

}