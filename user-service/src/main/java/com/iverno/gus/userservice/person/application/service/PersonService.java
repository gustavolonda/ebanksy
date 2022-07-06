package com.iverno.gus.userservice.person.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.iverno.gus.commons.general.application.bo.PersonBO;
import com.iverno.gus.commons.general.application.exception.BaseException;
import com.iverno.gus.commons.general.application.service.EndPointServiceImpl;
import com.iverno.gus.userservice.person.application.adapter.PersonAdapter;
import com.iverno.gus.userservice.person.domain.entities.PersonEntity;
import com.iverno.gus.userservice.person.domain.repository.PersonRepository;

import static com.iverno.gus.userservice.person.config.Constants.*;

@Service
@Qualifier("personService")
public class PersonService extends EndPointServiceImpl< PersonBO , PersonBO , PersonEntity, String>  {
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
		PersonEntity personEntity = this.getById(entity.getId());
		return entity;
	}

	@Override
	public PersonEntity statusChangeDelete(PersonEntity entity) {
		entity.setStatus(false);
		return entity;
	}

	@Override
	public PersonEntity modelBOToEntity(Object modelBO) {
		if (modelBO != null) {
			return PersonAdapter.personBOToPersonEntity((PersonBO)modelBO);
		}
		return null;
	}



	@Override
	public PersonBO entityToModelDTO(PersonEntity entity) {
		return null;
	}
	
	@Override
	public String nameModule() {
		return MODULE_USER;
	}
	@Override
	public String className() {
		return className;
	}

	@Override
	public BaseException validateBeforeSave(PersonEntity entity) {
		return null;
	}

}
