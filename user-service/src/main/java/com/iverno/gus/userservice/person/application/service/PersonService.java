package com.iverno.gus.userservice.person.application.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.iverno.gus.commons.general.application.bo.PersonBO;
import com.iverno.gus.commons.general.application.service.EndPointServiceImpl;
import static com.iverno.gus.commons.general.config.Constants.*;
import com.iverno.gus.commons.general.domain.model.ResponseBase;
import static com.iverno.gus.commons.general.domain.model.StatusResponseDomain.*;
import com.iverno.gus.userservice.person.application.adapter.PersonAdapter;
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
		return entity;
	}

	@Override
	public PersonEntity modelBOToEntity(PersonBO personBO) {
		if (personBO != null) {
			return PersonAdapter.personBOToPersonEntity(personBO);
		}
		return null;
	}



	@Override
	public PersonBO entityToModelBO(PersonEntity entity) {
		return PersonAdapter.personEntityToPersonBO(entity);
	}
	
	@Override
	public String nameModule() {
		return MODULE_USER;
	}
	@Override
	public String className() {
		return className;
	}

}
