package com.iverno.gus.account.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iverno.gus.account.domain.entities.AccountEntity;
@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, String> {
	List<AccountEntity> findByAccountNum(String accountNum);

}
