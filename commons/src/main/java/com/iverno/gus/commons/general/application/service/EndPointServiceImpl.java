package com.iverno.gus.commons.general.application.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import com.iverno.gus.commons.general.application.exception.BaseException;
import com.iverno.gus.commons.general.domain.model.ResponseBase;

import static com.iverno.gus.commons.general.config.Constants.*;
import static com.iverno.gus.commons.general.domain.model.ResponseBaseStatusDomain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.SneakyThrows;
@Service
public abstract class EndPointServiceImpl< D, M , T, ID>   implements IEndPointService<T, ID>{
	
	@Override
	@SneakyThrows
	public T save(T entity) {
		try {
			 BaseException baseException = validateBeforeSave(entity);
			 if(baseException != null)
				 throw baseException;
			 entity = runCreate(entity);
	       	return getDao().save(entity);
		} catch (Exception e) {
			throw new BaseException().builder()
									.status(ERROR)
									.message(UNEXPECTED_ERROR)
									.module(nameModule())
									.exception(e)
									.build();
		}
		
	}
	
	@Override
	@SneakyThrows
	public List<T> saveAll(List<T> entityList) {
		try {
			entityList.forEach( entity -> {
				entity = this.runCreate(entity);
			});
			List<T>  entityListResult = this.getDao().saveAll(entityList);
			
			return entityListResult;
		}catch (Exception e) {
			throw new BaseException().builder()
									.status(ERROR)
									.message(UNEXPECTED_ERROR)
									.module(nameModule())
									.exception(e)
									.build();
		}
	}

	@Override
	@SneakyThrows
	public T update(T entity) {
		try {
			entity = runUpdate(entity);
			return getDao().save(entity);
		}catch (Exception e) {
			throw new BaseException().builder()
									.status(ERROR)
									.message(UNEXPECTED_ERROR)
									.module(nameModule())
									.exception(e)
									.build();
		}
	}

	@Override
	@SneakyThrows
	public T delete(ID id) {
		try {
			T entity = getById(id);
			entity = statusChangeDelete(entity);
			return this.update(entity);
		
		}catch (Exception e) {
				throw new BaseException().builder()
										.status(ERROR)
										.message(UNEXPECTED_ERROR)
										.module(nameModule())
										.exception(e)
										.build();
		}
	}

	@Override
	@SneakyThrows
	public T getById(ID id) {
		try {	
			Optional<T> obj = getDao().findById(id);
			if (obj.isPresent()) {
				return obj.get();
			}
			return null;
		}catch (Exception e) {
			throw new BaseException().builder()
									.status(ERROR)
									.message(UNEXPECTED_ERROR)
									.module(nameModule())
									.exception(e)
									.build();
	}
	}

	@Override
	@SneakyThrows
	public List<T> getAll() {
		try {	
			List<T> returnList = new ArrayList<>();
			getDao().findAll().forEach(obj -> returnList.add(obj));
			return returnList;
		}catch (Exception e) {
			throw new BaseException().builder()
									.status(ERROR)
									.message(UNEXPECTED_ERROR)
									.module(nameModule())
									.exception(e)
									.build();
		}
	}

	@Override
	@SneakyThrows
	public ResponseBase toResponseBase(Object object) {
		try {
				List<D> modelDTOList = new ArrayList();
				if (object != null) {
					if(object instanceof List <?>) {
						List<T> entityList = (List<T>) object;
						if (entityList.size() > 0) {
							modelDTOList = entityList.stream().map(e -> entityToModelDTO(e))
							.collect(Collectors.toList());
						}
					}else {
						D modelDTO = entityToModelDTO((T) object);
						modelDTOList.add(modelDTO);
						
					}
				}
					
				
				
				return new ResponseBase<>().builder()
											.status(OK)
											.message(FINISHED_SUCCESSFULLY)
											.result(modelDTOList)
											.className(className())
											.module(nameModule())
											.build() ;
		}catch (Exception e) {
			throw new BaseException().builder()
									.status(ERROR)
									.message(UNEXPECTED_ERROR)
									.module(nameModule())
									.exception(e)
									.build();
		}
	}

	@Override
	public T modelBOToEntity(Object object) {
		return null;
		
	}
	public abstract JpaRepository<T, ID> getDao();
	public abstract T runCreate(T entity);
	public abstract T runUpdate(T entity);
	public abstract T statusChangeDelete(T entity);
	public abstract D entityToModelDTO(T entity);
	public abstract String nameModule();
	public abstract String className();
	public abstract BaseException validateBeforeSave(T entity);
	
}

