package com.iverno.gus.transaction.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.iverno.gus.commons.general.application.bo.TransactionBO;
import com.iverno.gus.commons.general.application.bo.TransactionTypeDomain;
import com.iverno.gus.commons.general.application.dto.AccountDTO;
import com.iverno.gus.commons.general.application.exception.BaseException;
import com.iverno.gus.commons.general.application.service.EndPointServiceImpl;
import com.iverno.gus.commons.general.domain.model.ResponseBase;

import static com.iverno.gus.commons.general.config.Constants.UNEXPECTED_ERROR;
import static com.iverno.gus.commons.general.domain.model.ResponseBaseStatusDomain.ERROR;
import static com.iverno.gus.config.Constants.*;

import static com.iverno.gus.transaction.application.adapter.TransactionAdapter.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.iverno.gus.transaction.application.dto.TransactionDTO;
import com.iverno.gus.transaction.application.openfeign.AccountOpenFeignServiceImpl;
import com.iverno.gus.transaction.application.validate.TransactionValidate;
import com.iverno.gus.transaction.domain.entity.TransactionEntity;
import com.iverno.gus.transaction.domain.repository.TransactionRespository;

import lombok.SneakyThrows;

@Service
@Qualifier("transactionService")
public class TransactionService extends EndPointServiceImpl< TransactionDTO, TransactionBO , TransactionEntity, String>{
	private  String className = this.getClass().getSimpleName(); 
	@Autowired
	TransactionRespository repository;
	@Autowired
	AccountOpenFeignServiceImpl accountOpenFeignServiceImpl;
	@Autowired
	TransactionValidate transactionValidate;
	@Override
	public JpaRepository<TransactionEntity, String> getDao() {
		return repository;
	}
	@Override
	public TransactionEntity runCreate(TransactionEntity entity) {
		AccountDTO  accountDTO = accountOpenFeignServiceImpl.getAccountById(entity.getAccountId());
		if(entity.getValue() > 0 && entity.getTransactionType().equalsIgnoreCase(TransactionTypeDomain.WITHDRAW))
			entity.setValue(-entity.getValue());
		double availableBalance = accountDTO.getAvailableBalance() + entity.getValue();
		AccountDTO  accountDTOResult = accountOpenFeignServiceImpl.availableBalanceUpdate(entity.getAccountId(), availableBalance);
		entity.setAvailableBalance(availableBalance);
		entity.setActive(true);
		return entity;
	}
	@Override
	public TransactionEntity runUpdate(TransactionEntity entity) {
		TransactionEntity transactionEntity = this.getById(entity.getId());
		entity.setCreateDate(transactionEntity.getCreateDate());
		transactionEntity.setStatus(entity.isStatus());
		return transactionEntity;
	}
	@Override
	public TransactionEntity statusChangeDelete(TransactionEntity entity) {
		entity.setActive(false);
		return entity;
	}
	@Override
	public TransactionEntity modelBOToEntity( Object modelBO) {
		
		return transactionBOToTransactionEntity((TransactionBO)modelBO);
	}
	@SneakyThrows
	@Override
	public TransactionDTO entityToModelDTO(TransactionEntity entity) {
		
		try{
			TransactionDTO transactionDTO = transactionEntityToTransactionDTO(entity);
			if(entity.getAccountId() != null && !entity.getAccountId().isEmpty()) {
				AccountDTO accountDTO = accountOpenFeignServiceImpl.getAccountById(entity.getAccountId());
				if (accountDTO != null) 
					transactionDTO = transactionEntityToTransactionDTO(entity, accountDTO);

			}
			return transactionDTO;
		}catch (Exception e){
	        
        	throw e;
        }
	}
	@Override
	public String nameModule() {
		return MODULE_TRASACTION;
	}
	@Override
	public String className() {
		return className;
	}
	@Override
	public BaseException validateBeforeSave(TransactionEntity entity) {
		return transactionValidate.validateBeforeSave(entity);
	}
	@SneakyThrows
	public ResponseBase<?> getListActive() {
		
		try{
			List<TransactionEntity> transactionDTOList = this.repository.findAllByActiveOrderByModifyDateDesc(true);
			
			return toResponseBase(transactionDTOList);
		}catch (Exception e){
	        
			throw new BaseException().builder()
			.status(ERROR)
			.message(UNEXPECTED_ERROR)
			.module(nameModule())
			.exception(e)
			.build();
        }
	}
	@SneakyThrows
	public ResponseBase<?> getBySearchText(String searchText) {
		
		try{
			List<TransactionEntity> transactionDTOList = new ArrayList<>();
			List<AccountDTO> accountDTOList = accountOpenFeignServiceImpl.getBySearchText(searchText);
			if(accountDTOList.size() > 0) {
				List<String> accountIdList = accountDTOList.stream().map(a -> a.getId()).collect(Collectors.toList());
				transactionDTOList = this.repository.findDistinctByActiveAndAccountIdIn(true, accountIdList);
	
			}
			
			return toResponseBase(transactionDTOList);
		}catch (Exception e){
	        
			throw new BaseException().builder()
			.status(ERROR)
			.message(UNEXPECTED_ERROR)
			.module(nameModule())
			.exception(e)
			.build();
        }
	}
}
