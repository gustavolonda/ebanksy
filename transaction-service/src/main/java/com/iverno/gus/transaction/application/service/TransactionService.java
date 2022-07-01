package com.iverno.gus.transaction.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.iverno.gus.commons.general.application.bo.TransactionBO;
import com.iverno.gus.commons.general.application.service.EndPointServiceImpl;
import static com.iverno.gus.config.Constants.MODULE_TRASACTION;

import com.iverno.gus.transaction.application.adapter.TransactionAdapter;
import com.iverno.gus.transaction.domain.entity.TransactionEntity;
import com.iverno.gus.transaction.domain.repository.TransactionRespository;


@Service
@Qualifier("transactionService")
public class TransactionService extends EndPointServiceImpl< TransactionBO , TransactionEntity, String>{
	private  String className = this.getClass().getSimpleName(); 
	@Autowired
	TransactionRespository repository;
	@Override
	public JpaRepository<TransactionEntity, String> getDao() {
		return repository;
	}
	@Override
	public TransactionEntity runCreate(TransactionEntity entity) {
		return entity;
	}
	@Override
	public TransactionEntity runUpdate(TransactionEntity entity) {
		return entity;
	}
	@Override
	public TransactionEntity statusChangeDelete(TransactionEntity entity) {
		entity.setStatus(false);
		return entity;
	}
	@Override
	public TransactionEntity modelBOToEntity(TransactionBO modelBO) {
		
		return TransactionAdapter.transactionBOToTransactionEntity(modelBO);
	}
	@Override
	public TransactionBO entityToModelBO(TransactionEntity entity) {
		return TransactionAdapter.transactionEntityToTransactionBO(entity);
	}
	@Override
	public String nameModule() {
		return MODULE_TRASACTION;
	}
	@Override
	public String className() {
		return className;
	}
}
