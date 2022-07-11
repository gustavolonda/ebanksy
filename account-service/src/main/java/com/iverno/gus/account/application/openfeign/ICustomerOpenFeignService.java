package com.iverno.gus.account.application.openfeign;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.iverno.gus.commons.general.application.dto.CustomerDTO;
public interface ICustomerOpenFeignService {
	CustomerDTO getCustomerById(String id);
	List<CustomerDTO> getBySearchText(String searchText);
}
