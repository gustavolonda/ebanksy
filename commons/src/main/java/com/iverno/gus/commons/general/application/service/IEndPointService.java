package com.iverno.gus.commons.general.application.service;

import java.util.List;

import com.iverno.gus.commons.general.domain.model.ResponseBase;

public interface IEndPointService  < T, ID> {
	
	List<T> saveAll(List<T> entityList);
	
	 T save(T entity);

	 T update(T entity);
	
	 T delete(ID id);
	
	 T getById(ID id);
	
	 List<T> getAll();
	 
	 ResponseBase toResponseBase(Object object);
	 
	 T modelBOToEntity(Object object);
	 
}