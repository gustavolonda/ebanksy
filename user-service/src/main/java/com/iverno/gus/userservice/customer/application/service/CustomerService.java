package com.iverno.gus.userservice.customer.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.iverno.gus.commons.general.application.bo.CustomerBO;
import com.iverno.gus.commons.general.application.dto.CustomerDTO;
import com.iverno.gus.commons.general.application.exception.BaseException;
import com.iverno.gus.commons.general.application.service.EndPointServiceImpl;
import static com.iverno.gus.userservice.customer.application.adapter.CustomerAdapter.*;
import com.iverno.gus.userservice.customer.domain.entities.CustomerEntity;
import com.iverno.gus.userservice.customer.domain.repository.CustomerRepository;
import com.iverno.gus.userservice.person.application.service.PersonService;
import com.iverno.gus.userservice.person.domain.entities.PersonEntity;

import lombok.SneakyThrows;

import static com.iverno.gus.userservice.person.config.Constants.*;

@Service
@Qualifier("customerService")
public class CustomerService extends EndPointServiceImpl<CustomerDTO, CustomerBO, CustomerEntity, String> {
	private String className = this.getClass().getSimpleName();
	@Autowired
	CustomerRepository repository;
	@Autowired
	PersonService personService;

	@Override
	public JpaRepository<CustomerEntity, String> getDao() {
		return this.repository;
	}

	@SneakyThrows
	@Override
	public CustomerEntity runCreate(CustomerEntity entity) {
		try {
			PersonEntity personEntity = personService.save(entity.getPersonEntity());
			entity.setPersonEntity(personService.save(personEntity));
			return entity;
		} catch (Exception e) {

			throw e;
		}
	}

	@SneakyThrows
	@Override
	public CustomerEntity runUpdate(CustomerEntity entity) {
		try {
			CustomerEntity customerEntity = this.getById(entity.getId());
			PersonEntity personEntity = customerEntity.getPersonEntity() ;
			
			entity.getPersonEntity().setId(personEntity.getId());
			entity.getPersonEntity().setCreateDate(personEntity.getCreateDate());
			PersonEntity personEntityResult = personService.update(entity.getPersonEntity());
			entity.setPersonEntity(personEntityResult);
			entity.setCreateDate(customerEntity.getCreateDate());
			return entity;
		} catch (Exception e) {

			throw e;
		}
	}

	@Override
	public CustomerEntity statusChangeDelete(CustomerEntity entity) {
		entity.setStatus(false);
		return entity;

	}

	@Override
	public CustomerEntity modelBOToEntity( Object modelBO) {
		if (modelBO != null) {
			return customerBOToCustomerEntity((CustomerBO)modelBO);
		}
		return null;
	}

	@Override
	public CustomerDTO entityToModelDTO(CustomerEntity entity) {
		return customerEntityToCustomerDTO(entity);
	}

	@Override
	public String nameModule() {
		return MODULE_CUSTOMER;
	}

	@Override
	public String className() {
		return className;
	}

	@Override
	public BaseException validateBeforeSave(CustomerEntity entity) {
		return null;
	}

}
