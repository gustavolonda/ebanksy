package com.iverno.gus.userservice.customer.application.service;

import static com.iverno.gus.commons.general.config.Constants.FINISHED_SUCCESSFULLY;
import static com.iverno.gus.commons.general.domain.model.StatusResponseDomain.OK;
import static com.iverno.gus.userservice.person.config.Constants.MODULE_CUSTOMER;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.iverno.gus.commons.general.application.service.EndPointServiceImpl;
import com.iverno.gus.commons.general.domain.model.ResponseBase;
import com.iverno.gus.userservice.customer.application.adapter.CustomerAdapter;
import com.iverno.gus.userservice.customer.application.bo.CustomerBO;
import com.iverno.gus.userservice.customer.domain.entities.CustomerEntity;
import com.iverno.gus.userservice.customer.domain.repository.CustomerRepository;
import com.iverno.gus.userservice.person.application.adapter.PersonAdapter;
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
		entity.setPersonEntity(personService.responseBaseToEntity(personService.save(PersonAdapter.personEntityToPersonBO(entity.getPersonEntity()))));
		return entity;
	}

	@Override
	public CustomerEntity runUpdate(CustomerEntity entity) {
		entity.setPersonEntity(personService.responseBaseToEntity(personService.update(PersonAdapter.personEntityToPersonBO(entity.getPersonEntity()))));
		return entity;
	}

	@Override
	public CustomerEntity statusChangeDelete(CustomerEntity entity) {
		entity.setStatus(false);
		return responseBaseToEntity(this.update(CustomerAdapter.customerEntityToCustomerBO(entity)));

	}

	@Override
	public ResponseBase toResponseBase(CustomerEntity entity) {
		List<CustomerBO> customerBOList = new ArrayList();
		if (entity != null) {
			CustomerBO customerBO = CustomerAdapter.customerEntityToCustomerBO(entity);
			customerBOList.add(customerBO);
		}
		return new ResponseBase<>().builder()
									.status(OK)
									.message(FINISHED_SUCCESSFULLY)
									.result(customerBOList)
									.className(className)
									.module(MODULE_CUSTOMER)
									.build() ;
	}

	@Override
	public ResponseBase toResponseBase(List<CustomerEntity> entityList) {
		List<CustomerBO> customerBOList = CustomerAdapter.customerEntityListToCustomerBOList(entityList);
		
		return new ResponseBase<>().builder()
									.status(OK)
									.message(FINISHED_SUCCESSFULLY)
									.result(customerBOList)
									.className(className)
									.module(MODULE_CUSTOMER)
									.build() ;
	}

	@Override
	public CustomerEntity responseBaseToEntity(ResponseBase response) {
		if (response != null && response.getResult() != null) {
			if (response.getResult() instanceof CustomerBO ) {
				return CustomerAdapter.customerBOToCustomerEntity((CustomerBO) response.getResult());
			}else if (response.getResult() instanceof List ) {
				List<CustomerBO> customerBOList = (List<CustomerBO>) response.getResult();
				if (customerBOList.size() > 0) {
					return CustomerAdapter.customerBOToCustomerEntity(customerBOList.get(0));
				}
				
			}
		
		}
		return null;
	}

	@Override
	public CustomerEntity requestToEntity(CustomerBO request) {
		if (request != null) {
			return CustomerAdapter.customerBOToCustomerEntity(request);
		}
		return null;
	}

	@Override
	public String nameModule() {
		return MODULE_CUSTOMER;
	}

}
