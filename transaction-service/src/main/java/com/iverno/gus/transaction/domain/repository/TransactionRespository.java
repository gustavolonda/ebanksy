package com.iverno.gus.transaction.domain.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iverno.gus.transaction.domain.entity.TransactionEntity;


@Repository
public interface TransactionRespository extends JpaRepository<TransactionEntity, String> {
	List<TransactionEntity> findByCreateDateBetween(Date to, Date from);
	List<TransactionEntity> findAllByActiveOrderByModifyDateDesc(boolean active);

}
