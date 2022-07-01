package com.iverno.gus.userservice.customer.application.service;

import static com.iverno.gus.userservice.person.config.Constants.MODULE_CUSTOMER;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.iverno.gus.commons.general.application.bo.CustomerBO;
import com.iverno.gus.commons.general.application.service.EndPointServiceImpl;
import com.iverno.gus.userservice.customer.application.adapter.CustomerAdapter;
import com.iverno.gus.userservice.customer.domain.entities.CustomerEntity;
import com.iverno.gus.userservice.customer.domain.repository.CustomerRepository;
import com.iverno.gus.userservice.person.application.service.PersonService;
@Service
@Qualifier("customerService")
public class CustomerService extends EndPointServiceImpl< CustomerBO , CustomerEntity, String>  {
	private  String className = this.getClass().getSimpleName(); 
	@Autowired
	CustomerRepository repository;
	@Autowired
	PersonService personService;
	@Override
	public JpaRepository<CustomerEntity, String> getDao() {
		return this.repository;
	}

	@Override
	public CustomerEntity runCreate(CustomerEntity entity) {
		entity.setPersonEntity(personService.save(entity.getPersonEntity()));
		return entity;
	}

	@Override
	public CustomerEntity runUpdate(CustomerEntity entity) {
		entity.setPersonEntity(personService.update(entity.getPersonEntity()));
		return entity;
	}

	@Override
	public CustomerEntity statusChangeDelete(CustomerEntity entity) {
		entity.setStatus(false);
		return entity;

	}
	
	@Override
	public CustomerEntity modelBOToEntity(CustomerBO request) {
		if (request != null) {
			return CustomerAdapter.customerBOToCustomerEntity(request);
		}
		return null;
	}

	

	@Override
	public CustomerBO entityToModelBO(CustomerEntity entity) {
		return CustomerAdapter.customerEntityToCustomerBO(entity);
	}
	
	@Override
	public String nameModule() {
		return MODULE_CUSTOMER;
	}
	
	@Override
	public String className() {
		return className;
	}

}
