package com.iverno.gus.test.account.infrastructure.controller;
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
import com.iverno.gus.AccountServiceApplication;
import com.iverno.gus.account.application.service.AccountService;
import com.iverno.gus.account.domain.entities.AccountEntity;
import com.iverno.gus.account.infrastructure.controller.AccountController;
import com.iverno.gus.commons.general.domain.model.ResponseBase;
import com.iverno.gus.commons.general.infrastructure.controllertest.IEndPointTest;
import com.iverno.gus.commons.general.util.Util;

@ContextConfiguration(classes = AccountServiceApplication.class)
@WebMvcTest(AccountController.class)
@ComponentScan(basePackages = { "com.iverno.gus.account.infrastructure.controller" })
public class AccountControllerTest implements IEndPointTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private AccountService accountService;
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	@Override
	public void getAllEntitiesList() throws Exception {
		List<AccountEntity> entityList = getAll();
		ResponseBase<List<AccountEntity>> responde = responseBaseIntance(entityList);
		given(accountService.toResponseBase(accountService.getAll())).willReturn(responde);

		ResultActions response = mockMvc
				.perform(get("/" + REQUEST_MAPPING_ACCOUNTS).contentType(MediaType.APPLICATION_JSON));

		response.andExpect(status().isOk()).andDo(print());

	}

	@Test
	@Override
	public void getEntitiyById() throws Exception {
		// given - precondition or setup
		AccountEntity accountEntity = accountEntityInstance();
		String id = accountEntity.getId();
		List<AccountEntity> accountEntityList = new ArrayList();
		accountEntityList.add(accountEntity);
		ResponseBase<List<AccountEntity>> responde = responseBaseIntance(accountEntityList);
		given(accountService.toResponseBase(accountService.getById(id))).willReturn(responde);

		// when - action or the behaviour that we are going test
		ResultActions response = mockMvc.perform(get("/" + REQUEST_MAPPING_ACCOUNTS + "/" + "{id}", id));

		// then - verify the output
		response.andExpect(status().isOk()).andDo(print());

	}

	@Override
	public void savedEntity() throws Exception {
		// given - precondition or setup
		AccountEntity accountEntity = accountEntityInstance();
		String id = accountEntity.getId();
		List<AccountEntity> accountEntityList = new ArrayList();
		accountEntityList.add(accountEntity);
		ResponseBase<List<AccountEntity>> responde = responseBaseIntance(accountEntityList);

		given(accountService.toResponseBase(accountService.save(accountEntity))).willReturn(responde);

		// when - action or behaviour that we are going test
		ResultActions response = mockMvc.perform(post("/" + REQUEST_MAPPING_ACCOUNTS)
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(accountEntity)));

		// then - verify the result
		response.andDo(print()).andExpect(status().isOk());

	}

	@Test
	@Override
	public void updatedEntitiy() throws Exception {
		// given - precondition or setup
		AccountEntity accountEntity = accountEntityInstance();
		String id = accountEntity.getId();
		List<AccountEntity> accountEntityList = new ArrayList();
		accountEntityList.add(accountEntity);
		ResponseBase<List<AccountEntity>> responde = responseBaseIntance(accountEntityList);

		given(accountService.toResponseBase(accountService.update(accountEntity))).willReturn(responde);

		// when - action or behaviour that we are going test
		ResultActions response = mockMvc.perform(put("/" + REQUEST_MAPPING_ACCOUNTS)
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(accountEntity)));

		// then - verify the result
		response.andDo(print()).andExpect(status().isOk());

	}

	@Test
	@Override
	public void deleteEntitiy() throws Exception {
		// given - precondition or setup
		AccountEntity accountEntity = accountEntityInstance();
		String id = accountEntity.getId();
		List<AccountEntity> accountEntityList = new ArrayList();
		accountEntityList.add(accountEntity);
		ResponseBase<List<AccountEntity>> responde = responseBaseIntance(accountEntityList);

		given(accountService.toResponseBase(accountService.delete(id))).willReturn(responde);

		// when - action or behaviour that we are going test
		ResultActions response = mockMvc.perform(delete("/" + REQUEST_MAPPING_ACCOUNTS + "/" + "{id}", id)
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(accountEntity)));

		// then - verify the result
		response.andDo(print()).andExpect(status().isOk());

	}

	private List<AccountEntity> getAll() {
		List<AccountEntity> accountEntityList = new ArrayList();
		for (int i = 0; i < 4; i++) {
			accountEntityList.add(accountEntityInstance());

		}
		return accountEntityList;
	}

	private AccountEntity accountEntityInstance() {
		Date createDate = new Date(System.currentTimeMillis());
		String uuidCustomer = UUID.randomUUID().toString();
		String uuid = UUID.randomUUID().toString();
		AccountEntity accountEntity = new AccountEntity().builder()
														.id(uuid)
														.customerId(uuidCustomer)
														.accountNum(Util.accountNumGenerate())
														.accountType("S")
														.initialBalance(4556)
														.availableBalance(4556)
														.build();
		accountEntity.setCreateDate(createDate);
		accountEntity.setModifyDate(createDate);

		return accountEntity;

	}

	public ResponseBase responseBaseIntance(Object object) {
		return new ResponseBase<>().builder().status(OK).message(FINISHED_SUCCESSFULLY).result(object)
				.className(AccountControllerTest.class.getSimpleName()).module("Module Accoun Test").build();
	}

}

