package com.iverno.gus.account.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iverno.gus.account.domain.entities.AccountEntity;
@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, String> {
	List<AccountEntity> findByAccountNum(String accountNum);
	List<AccountEntity> findDistinctByAccountNumContainingIgnoreCase(String accountNum);
	List<AccountEntity> findDistinctByActiveOrderByModifyDateDesc(boolean active);
	List<AccountEntity> findDistinctByCustomerIdIn(List<String> customerIdList);
	List<AccountEntity> findDistinctByAccountNumContainingIgnoreCaseOrInitialBalanceOrAvailableBalance(String accountNum, double initialBalance,double availableBalance);

	List<AccountEntity> findTop10ByActiveOrderByModifyDateDesc(boolean active);

}
