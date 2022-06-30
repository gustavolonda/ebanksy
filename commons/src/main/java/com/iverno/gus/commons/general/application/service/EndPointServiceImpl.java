package com.iverno.gus.commons.general.application.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.iverno.gus.commons.general.application.exception.BaseException;
import com.iverno.gus.commons.general.domain.model.ResponseBase;
import com.iverno.gus.commons.general.domain.model.StatusResponseDomain;
import static com.iverno.gus.commons.general.config.Constants.UNEXPECTED_ERROR;
import lombok.SneakyThrows;
@Service
public abstract class EndPointServiceImpl< S , T, ID>  implements IEndPointService<S , T, ID>{
	
	@Override
	@SneakyThrows
	public ResponseBase save(S request) {
		try {
			T entity = requestToEntity(request);
			 entity = runCreate(entity);
	       	return toResponseBase(getDao().save(entity));
		} catch (Exception e) {
			throw new BaseException().builder()
									.status(StatusResponseDomain.ERROR)
									.message(UNEXPECTED_ERROR)
									.module(nameModule())
									.exception(e)
									.build();
		}
		
	}
	
	@Override
	@SneakyThrows
	public ResponseBase saveAll(List<T> entityList) {
		try {
			entityList.forEach( entity -> {
				entity = this.runCreate(entity);
			});
			List<T>  entityListResult = this.getDao().saveAll(entityList);
			
			return toResponseBase(entityListResult);
		}catch (Exception e) {
			throw new BaseException().builder()
									.status(StatusResponseDomain.ERROR)
									.message(UNEXPECTED_ERROR)
									.module(nameModule())
									.exception(e)
									.build();
		}
	}

	@Override
	@SneakyThrows
	public ResponseBase update(S request) {
		try {
			T entity = requestToEntity(request);
			entity = runUpdate(entity);
			return toResponseBase(getDao().save(entity));
		}catch (Exception e) {
			throw new BaseException().builder()
									.status(StatusResponseDomain.ERROR)
									.message(UNEXPECTED_ERROR)
									.module(nameModule())
									.exception(e)
									.build();
		}
	}

	@Override
	@SneakyThrows
	public ResponseBase delete(ID id) {
		try {
			T entity = responseBaseToEntity(get(id));
			return toResponseBase(statusChangeDelete(entity));
		
		}catch (Exception e) {
				throw new BaseException().builder()
										.status(StatusResponseDomain.ERROR)
										.message(UNEXPECTED_ERROR)
										.module(nameModule())
										.exception(e)
										.build();
		}
	}

	@Override
	@SneakyThrows
	public ResponseBase get(ID id) {
		try {	
			Optional<T> obj = getDao().findById(id);
			if (obj.isPresent()) {
				return toResponseBase(obj.get());
			}
			return toResponseBase(new ArrayList<>());
		}catch (Exception e) {
			throw new BaseException().builder()
									.status(StatusResponseDomain.ERROR)
									.message(UNEXPECTED_ERROR)
									.module(nameModule())
									.exception(e)
									.build();
	}
	}

	@Override
	@SneakyThrows
	public ResponseBase getAll() {
		try {	
			List<T> returnList = new ArrayList<>();
			getDao().findAll().forEach(obj -> returnList.add(obj));
			return toResponseBase(returnList);
		}catch (Exception e) {
			throw new BaseException().builder()
									.status(StatusResponseDomain.ERROR)
									.message(UNEXPECTED_ERROR)
									.module(nameModule())
									.exception(e)
									.build();
		}
	}
	
	

	public abstract JpaRepository<T, ID> getDao();
	public abstract T runCreate(T entity);
	public abstract T runUpdate(T entity);
	public abstract T statusChangeDelete(T entity);
	public abstract ResponseBase toResponseBase(T entity);
	public abstract ResponseBase toResponseBase(List<T> entityList);
	public abstract T responseBaseToEntity(ResponseBase response);
	public abstract T requestToEntity(S request);
	public abstract String nameModule();
	
}

