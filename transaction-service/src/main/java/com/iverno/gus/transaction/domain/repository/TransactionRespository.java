package com.iverno.gus.transaction.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.iverno.gus.transaction.domain.entity.TransactionEntity;


@Repository
public interface TransactionRespository extends JpaRepository<TransactionEntity, String> {
	
}
