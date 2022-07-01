package com.iverno.gus.userservice.customer.application.adapter;

import java.util.List;
import java.util.stream.Collectors;

import com.iverno.gus.commons.general.application.bo.CustomerBO;
import com.iverno.gus.userservice.customer.domain.entities.CustomerEntity;
import com.iverno.gus.userservice.person.application.adapter.PersonAdapter;

public class CustomerAdapter {

	public static CustomerBO customerEntityToCustomerBO(CustomerEntity entity) {
		
		return new CustomerBO().builder()
									.id(entity.getId())
									.personBO(PersonAdapter.personEntityToPersonBO(entity.getPersonEntity()))
									.password(entity.getPassword())
									.build();
	}

	public static List<CustomerBO> customerEntityListToCustomerBOList(List<CustomerEntity> entityList) {
		return entityList.stream().map(c -> customerEntityToCustomerBO(c))
											.collect(Collectors.toList());
	}

	public static CustomerEntity customerBOToCustomerEntity(CustomerBO customerBO) {
		return new CustomerEntity().builder()
										.id(customerBO.getId())
										.personEntity(PersonAdapter.personBOToPersonEntity(customerBO.getPersonBO()))
										.password(customerBO.getPassword())
										.build();
	}

}
