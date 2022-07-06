package com.iverno.gus.userservice.test.customer.infrastructure.controller;

import static com.iverno.gus.commons.general.domain.model.ResponseBaseStatusDomain.OK;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

import static com.iverno.gus.commons.general.config.Constants.*;
import com.iverno.gus.commons.general.domain.model.ResponseBase;
import com.iverno.gus.commons.general.infrastructure.controllertest.IEndPointTest;
import com.iverno.gus.userservice.UserServiceApplication;
import com.iverno.gus.userservice.customer.application.service.CustomerService;
import com.iverno.gus.userservice.customer.domain.entities.CustomerEntity;
import com.iverno.gus.userservice.customer.infrastructure.controller.CustomerController;
import com.iverno.gus.userservice.person.domain.entities.PersonEntity;
import com.iverno.gus.userservice.person.application.service.PersonService;

@ContextConfiguration(classes=UserServiceApplication.class)
@WebMvcTest(CustomerController.class)
@ComponentScan(basePackages = {"com.iverno.gus.userservice.customer.infrastructure.controller"}) 
public class CustomerControllerTest implements IEndPointTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private CustomerService customerService;
	@MockBean
	private PersonService PersonService;
	
	 @Autowired
	 private ObjectMapper objectMapper;

	@Test
	@Override
	public void getAllEntitiesList() throws Exception {
		List<CustomerEntity> customerEntityList = getAll();
		ResponseBase<List<CustomerEntity>> responde = responseBaseIntance(customerEntityList);
		given(customerService.toResponseBase(customerService.getAll())).willReturn(responde);
		

	    ResultActions response = mockMvc.perform(get("/"+REQUEST_MAPPING_CUSTOMERS)
	        .contentType(MediaType.APPLICATION_JSON));
	
	    response.andExpect(status().isOk())
	            .andDo(print());

		
	}

	
	@Test
	@Override
	public void getEntitiyById() throws Exception {
		// given - precondition or setup
		CustomerEntity customerEntity = customerEntityInstance() ;
		String id = customerEntity.getId();  
		List<CustomerEntity>  customerEntityList = new ArrayList();
		customerEntityList.add(customerEntity);
		ResponseBase<List<CustomerEntity>> responde = responseBaseIntance(customerEntityList);
		given(customerService.toResponseBase(customerService.getById(id))).willReturn(responde);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/"+REQUEST_MAPPING_CUSTOMERS+"/"+"{id}", id));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print());



		
	}
	@Test
	@Override
	public void savedEntity() throws Exception {
		 // given - precondition or setup
		CustomerEntity customerEntity = customerEntityInstance() ;
		String id = customerEntity.getId();  
		List<CustomerEntity>  customerEntityList = new ArrayList();
		customerEntityList.add(customerEntity);
		ResponseBase<List<CustomerEntity>> responde = responseBaseIntance(customerEntityList);
		
        given(customerService.toResponseBase(customerService.save(customerEntity)))
                .willReturn(responde);

        // when - action or behaviour that we are going test
        ResultActions response = mockMvc.perform(post("/"+REQUEST_MAPPING_CUSTOMERS)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(customerEntity)));

        // then - verify the result 
        response.andDo(print()).
                andExpect(status().isOk());


		
	}
	@Test
	@Override
	public void updatedEntitiy() throws Exception {
		 // given - precondition or setup
		CustomerEntity customerEntity = customerEntityInstance() ;
		String id = customerEntity.getId();  
		List<CustomerEntity>  customerEntityList = new ArrayList();
		customerEntityList.add(customerEntity);
		ResponseBase<List<CustomerEntity>> responde = responseBaseIntance(customerEntityList);
		
        given(customerService.toResponseBase(customerService.update(customerEntity)))
                .willReturn(responde);

        // when - action or behaviour that we are going test
        ResultActions response = mockMvc.perform(put("/"+REQUEST_MAPPING_CUSTOMERS)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(customerEntity)));

        // then - verify the result 
        response.andDo(print()).
                andExpect(status().isOk());
		
	}
	@Test
	@Override
	public void deleteEntitiy() throws Exception {
		 // given - precondition or setup
		CustomerEntity customerEntity = customerEntityInstance() ;
		String id = customerEntity.getId();  
		List<CustomerEntity>  customerEntityList = new ArrayList();
		customerEntity.setStatus(false);
		customerEntityList.add(customerEntity);
		ResponseBase<List<CustomerEntity>> responde = responseBaseIntance(customerEntityList);
		
        given(customerService.toResponseBase(customerService.delete(id)))
                .willReturn(responde);

        // when - action or behaviour that we are going test
        ResultActions response = mockMvc.perform(delete("/"+REQUEST_MAPPING_CUSTOMERS+"/"+"{id}", id)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(customerEntity)));

        // then - verify the result 
        response.andDo(print()).
                andExpect(status().isOk());
		
	}
	
	private List<CustomerEntity> getAll() {
		List<CustomerEntity>  customerEntityList = new ArrayList();
		for (int i = 0; i < 4; i++) {
			customerEntityList.add(customerEntityInstance());
			
		}
		return customerEntityList;
	}
	private CustomerEntity customerEntityInstance() {
		Date createDate = new Date(System.currentTimeMillis());
		String uuidCustomer = UUID.randomUUID().toString();
		String uuidPerson   = UUID.randomUUID().toString();
		PersonEntity personEntity = new PersonEntity().builder()
													.id(uuidPerson)
													.name("nameTest")
													.gender("M")
													.idCard("idCardtest")
													.age(30)
													.address("addresTest")
													.phone("phoneTest")
													.build();
		personEntity.setCreateDate(createDate);
		CustomerEntity customerEntity = CustomerEntity.builder()
													.id(uuidCustomer)
													.personEntity(personEntity)
													.password("passwordTest")
									                .build();
		customerEntity.setCreateDate(createDate);
		return customerEntity;

	}
	
	public ResponseBase responseBaseIntance(Object object) {
		return new ResponseBase<>().builder()
				.status(OK)
				.message(FINISHED_SUCCESSFULLY)
				.result(object)
				.className("CustomerControllerTest")
				.module("Module Customer Test")
				.build() ;
	}


}
