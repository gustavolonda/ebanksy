package com.iverno.gus.account.application.openfeign;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.iverno.gus.commons.general.application.dto.CustomerDTO;
import com.iverno.gus.commons.general.domain.model.ResponseBase;
import com.iverno.gus.commons.general.util.Util;

import lombok.SneakyThrows;

@Component
public class CustomerOpenFeignServiceImpl implements ICustomerOpenFeignService {
	@Autowired
	ICustomerClient iCustomerClient;
	
	@SneakyThrows
	@Override
	public CustomerDTO getCustomerById(String id) {
		try{
			CustomerDTO customerDTO = null;
			List<Object> customerDTOList = new ArrayList();
			ResponseBase responseBase =  iCustomerClient.getCustomerById(id);
			if(responseBase.getResult() != null) {
				List<?> resultList = (List<?>) responseBase.getResult();
				customerDTOList = resultList.stream().map(c -> Util.linkedHashMapToObject((LinkedHashMap<String, Object>) c,CustomerDTO.class)).collect(Collectors.toList());
				customerDTO = customerDTOList.size() > 0? (CustomerDTO) customerDTOList.get(0):null;
			}
			return customerDTO;

        }catch (Exception e){
        
        	throw e;
        }
	}

}


	
