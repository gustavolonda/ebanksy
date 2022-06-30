package com.iverno.gus.userservice.person.application.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.iverno.gus.commons.general.application.service.EndPointServiceImpl;
import static com.iverno.gus.commons.general.config.Constants.*;
import com.iverno.gus.commons.general.domain.model.ResponseBase;
import static com.iverno.gus.commons.general.domain.model.StatusResponseDomain.*;
import com.iverno.gus.userservice.person.application.adapter.PersonAdapter;
import com.iverno.gus.userservice.person.application.bo.PersonBO;
import static com.iverno.gus.userservice.person.config.Constants.*;
import com.iverno.gus.userservice.person.domain.entities.PersonEntity;
import com.iverno.gus.userservice.person.domain.repository.PersonRepository;

@Service
@Qualifier("personService")
public class PersonService extends EndPointServiceImpl< PersonBO , PersonEntity, String>  {
	private  String className = this.getClass().getSimpleName(); 
	@Autowired
	PersonRepository repository;
	@Override
	public JpaRepository<PersonEntity, String> getDao() {
		return repository;
	}

	@Override
	public PersonEntity runCreate(PersonEntity entity) {
		return entity;
	}

	@Override
	public PersonEntity runUpdate(PersonEntity entity) {
		return entity;
	}

	@Override
	public PersonEntity statusChangeDelete(PersonEntity entity) {
		entity.setStatus(false);
		return responseBaseToEntity(this.update(PersonAdapter.personEntityToPersonBO(entity)));
	}

	@Override
	public ResponseBase toResponseBase(PersonEntity entity) {
		List<PersonBO> personBOList = new ArrayList();
		if (entity != null) {
			PersonBO personBO = PersonAdapter.personEntityToPersonBO(entity);
			personBOList.add(personBO);
		}
		return new ResponseBase<>().builder()
									.status(OK)
									.message(FINISHED_SUCCESSFULLY)
									.result(personBOList)
									.className(className)
									.module(MODULE_USER)
									.build() ;
	}

	@Override
	public ResponseBase toResponseBase(List<PersonEntity> entityList) {
		List<PersonBO> personBOs = PersonAdapter.personEntityListToPersonBOList(entityList);
		
		return new ResponseBase<>().builder()
									.status(OK)
									.message(FINISHED_SUCCESSFULLY)
									.result(personBOs)
									.className(className)
									.module(MODULE_USER)
									.build() ;
	}

	@Override
	public PersonEntity responseBaseToEntity(ResponseBase response) {
		if (response != null && response.getResult() != null) {
			if (response.getResult() instanceof PersonBO ) {
				return PersonAdapter.personBOToPersonEntity((PersonBO) response.getResult());
			}else if (response.getResult() instanceof List ) {
				List<PersonBO> personBOs = (List<PersonBO>) response.getResult();
				if (personBOs.size() > 0) {
					return PersonAdapter.personBOToPersonEntity(personBOs.get(0));
				}
				
			}
		
		}
		return null;
	}

	@Override
	public PersonEntity requestToEntity(PersonBO request) {
		if (request != null) {
			return PersonAdapter.personBOToPersonEntity(request);
		}
		return null;
	}

	@Override
	public String nameModule() {
		return MODULE_USER;
	}

}
