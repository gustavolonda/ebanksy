package com.iverno.gus.transaction.application.openfeign;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iverno.gus.commons.general.application.dto.AccountDTO;
import com.iverno.gus.commons.general.domain.model.ResponseBase;
import com.iverno.gus.commons.general.util.Util;

import lombok.SneakyThrows;
@Component
public class AccountOpenFeignServiceImpl implements IAccountOpenFeignService {
	@Autowired
	IAccountClient iAccountClient;
	@Override
	@SneakyThrows
	public AccountDTO getAccountById(String id) {
		try{
			AccountDTO accountDTO = null;
			
			List<Object> accountDTOList = new ArrayList();
			ResponseBase responseBase =  iAccountClient.getAccountById(id);
			if(responseBase.getResult() != null) {
				List<?> resultList = (List<?>) responseBase.getResult();
				accountDTOList = resultList.stream().map(c -> Util.linkedHashMapToObject((LinkedHashMap<String, Object>) c,AccountDTO.class)).collect(Collectors.toList());
				accountDTO = accountDTOList.size() > 0? (AccountDTO) accountDTOList.get(0):null;
			}


			return accountDTO;

        }catch (Exception e){
        
        	throw e;
        }
	}
	@SneakyThrows
	@Override
	public AccountDTO availableBalanceUpdate(String id, double availableBalanceNew) {
		try{
			
			AccountDTO accountDTO = null;
			
			List<Object> accountDTOList = new ArrayList();
			ResponseBase responseBase =  iAccountClient.availableBalanceUpdate(id,availableBalanceNew);
			if(responseBase.getResult() != null) {
				List<?> resultList = (List<?>) responseBase.getResult();
				accountDTOList = resultList.stream().map(c -> Util.linkedHashMapToObject((LinkedHashMap<String, Object>) c,AccountDTO.class)).collect(Collectors.toList());
				accountDTO = accountDTOList.size() > 0? (AccountDTO) accountDTOList.get(0):null;
			}


			return accountDTO;

        }catch (Exception e){
        
        	throw e;
        }
	}
	@SneakyThrows
	@Override
	public List<AccountDTO> getBySearchText(String searchText) {
		try{
			List<AccountDTO> accountDTOList = new ArrayList();
			ResponseBase responseBase =  iAccountClient.getBySearchText(searchText);
			if(responseBase.getResult() != null) {
				List<?> resultList = (List<?>) responseBase.getResult();
				accountDTOList = resultList.stream().map(c -> (AccountDTO) Util.linkedHashMapToObject((LinkedHashMap<String, Object>) c,AccountDTO.class)).collect(Collectors.toList());
			}
			return accountDTOList;
		 }catch (Exception e){
		        
	        	throw e;
	        }
	}

}

