package com.iverno.gus.transaction.application.openfeign;
import java.util.List;

import com.iverno.gus.commons.general.application.dto.AccountDTO;

public interface IAccountOpenFeignService {
	AccountDTO getAccountById(String id);
	AccountDTO availableBalanceUpdate(String id, double availableBalanceNew) ;
	List<AccountDTO> getBySearchText(String searchText);
}