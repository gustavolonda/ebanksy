package com.iverno.gus.commons.general.application.service;

import java.util.List;

import com.iverno.gus.commons.general.domain.model.ResponseBase;

public interface IEndPointService  <S , T, ID> {
	
	ResponseBase saveAll(List<T> entityList);
	
	ResponseBase save(S request);

	ResponseBase update(S request);
	
	ResponseBase delete(ID id);
	
	ResponseBase get(ID id);
	
	ResponseBase getAll();
}