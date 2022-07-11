package com.iverno.gus.userservice.customer.application.adapter;

import java.util.List;
import java.util.stream.Collectors;

import com.iverno.gus.commons.general.application.bo.CustomerBO;
import com.iverno.gus.commons.general.application.dto.CustomerDTO;
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
		CustomerEntity customerEntity= new CustomerEntity().builder()
													.id(customerBO.getId())
													.personEntity(PersonAdapter.personBOToPersonEntity(customerBO.getPersonBO()))
													.password(customerBO.getPassword())
													.build();
		customerEntity.setStatus(customerBO.isStatus());
		return customerEntity;
	}

	public static CustomerDTO customerEntityToCustomerDTO(CustomerEntity entity) {
		
		return new CustomerDTO().builder()
									.id(entity.getId())
									.name(entity.getPersonEntity() != null ? entity.getPersonEntity().getName():"")
									.address(entity.getPersonEntity() != null ? entity.getPersonEntity().getAddress():"")
									.password(entity.getPassword())
									.status(entity.isStatus())
									.gender(entity.getPersonEntity() != null ? entity.getPersonEntity().getGender():"")
									.age(entity.getPersonEntity() != null ? entity.getPersonEntity().getAge():0)
									.idCard(entity.getPersonEntity() != null ? entity.getPersonEntity().getIdCard():"")
									.phone(entity.getPersonEntity() != null ? entity.getPersonEntity().getPhone():"")
									.build();
	}

}
