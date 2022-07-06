package com.iverno.gus.test.transaction.infrastructure.controller;
import static com.iverno.gus.commons.general.config.Constants.*;
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
import com.iverno.gus.TransactionServiceApplication;

import com.iverno.gus.commons.general.domain.model.ResponseBase;
import com.iverno.gus.commons.general.infrastructure.controllertest.IEndPointTest;
import com.iverno.gus.commons.general.util.Util;
import com.iverno.gus.transaction.application.service.TransactionService;
import com.iverno.gus.transaction.domain.entity.TransactionEntity;
import com.iverno.gus.transaction.infrastructure.controller.TransactionController;

@ContextConfiguration(classes = TransactionServiceApplication.class)
@WebMvcTest(TransactionController.class)
@ComponentScan(basePackages = { "com.iverno.gus.transaction.infrastructure.controller" })
public class TransactionControllerTests implements IEndPointTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private TransactionService transactionService;
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	@Override
	public void getAllEntitiesList() throws Exception {
		List<TransactionEntity> entityList = getAll();
		ResponseBase<List<TransactionEntity>> responde = responseBaseIntance(entityList);
		given(transactionService.toResponseBase(transactionService.getAll())).willReturn(responde);

		ResultActions response = mockMvc
				.perform(get("/" + REQUEST_MAPPING_TRANACTIONS).contentType(MediaType.APPLICATION_JSON));

		response.andExpect(status().isOk()).andDo(print());
		
	}

	@Test
	@Override
	public void getEntitiyById() throws Exception {
		// given - precondition or setup
		TransactionEntity accountEntity = transactionEntityInstance();
		String id = accountEntity.getId();
		List<TransactionEntity> accountEntityList = new ArrayList();
		accountEntityList.add(accountEntity);
		ResponseBase<List<TransactionEntity>> responde = responseBaseIntance(accountEntityList);
		given(transactionService.toResponseBase(transactionService.getById(id))).willReturn(responde);

		// when - action or the behaviour that we are going test
		ResultActions response = mockMvc.perform(get("/" + REQUEST_MAPPING_TRANACTIONS + "/" + "{id}", id));

		// then - verify the output
		response.andExpect(status().isOk())
				.andDo(print());
		
	}
	

	@Test
	@Override
	public void savedEntity() throws Exception {
		// given - precondition or setup
		TransactionEntity accountEntity = transactionEntityInstance();
		String id = accountEntity.getId();
		List<TransactionEntity> accountEntityList = new ArrayList();
		accountEntityList.add(accountEntity);
		ResponseBase<List<TransactionEntity>> responde = responseBaseIntance(accountEntityList);
		
		given(transactionService.toResponseBase(transactionService.save(accountEntity))).willReturn(responde);

		// when - action or behaviour that we are going test
		ResultActions response = mockMvc.perform(post("/" + REQUEST_MAPPING_TRANACTIONS)
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(accountEntity)));

		// then - verify the result
		response.andDo(print()).andExpect(status().isOk());
		
	}
	@Test
	@Override
	public void updatedEntitiy() throws Exception {
		// given - precondition or setup
		TransactionEntity accountEntity = transactionEntityInstance();
		String id = accountEntity.getId();
		List<TransactionEntity> accountEntityList = new ArrayList();
		accountEntityList.add(accountEntity);
		ResponseBase<List<TransactionEntity>> responde = responseBaseIntance(accountEntityList);
		
		given(transactionService.toResponseBase(transactionService.update(accountEntity))).willReturn(responde);

		// when - action or behaviour that we are going test
		ResultActions response = mockMvc.perform(put("/" + REQUEST_MAPPING_TRANACTIONS)
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(accountEntity)));

		// then - verify the result
		response.andDo(print()).andExpect(status().isOk());
		
	}
	@Test
	@Override
	public void deleteEntitiy() throws Exception {
		// given - precondition or setup
		TransactionEntity accountEntity = transactionEntityInstance();
		String id = accountEntity.getId();
		List<TransactionEntity> accountEntityList = new ArrayList();
		accountEntityList.add(accountEntity);
		ResponseBase<List<TransactionEntity>> responde = responseBaseIntance(accountEntityList);
		

		given(transactionService.toResponseBase(transactionService.delete(id))).willReturn(responde);

		// when - action or behaviour that we are going test
		ResultActions response = mockMvc.perform(delete("/" + REQUEST_MAPPING_TRANACTIONS+ "/" + "{id}", id)
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(accountEntity)));

		// then - verify the result
		response.andDo(print()).andExpect(status().isOk());

		
	}
	
	private List<TransactionEntity> getAll() {
		List<TransactionEntity> transactionEntityList = new ArrayList();
		for (int i = 0; i < 4; i++) {
			transactionEntityList.add(transactionEntityInstance());

		}
		return transactionEntityList;
	}

	private TransactionEntity transactionEntityInstance() {
		Date createDate = new Date(System.currentTimeMillis());
		String uuidAccount = UUID.randomUUID().toString();
		String uuid = UUID.randomUUID().toString();
		TransactionEntity transactionEntity = new TransactionEntity().builder()
														.id(uuid)
														.accountId(uuidAccount)
														.transactionType("W")
														.value(-10)
														.availableBalance(4556)
														.build();
		transactionEntity.setCreateDate(createDate);
		transactionEntity.setModifyDate(createDate);

		return transactionEntity;

	}

	public ResponseBase responseBaseIntance(Object object) {
		return new ResponseBase<>().builder()
									.status(OK)
									.message(FINISHED_SUCCESSFULLY)
									.result(object)
									.className(TransactionControllerTests.class.getSimpleName())
									.module("Module Transaction Test").build();
	}
}

