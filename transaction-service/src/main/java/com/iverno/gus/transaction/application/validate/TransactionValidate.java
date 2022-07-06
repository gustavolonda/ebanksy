package com.iverno.gus.transaction.application.validate;
import static com.iverno.gus.commons.general.config.Constants.*;
import static com.iverno.gus.commons.general.domain.model.ResponseBaseStatusDomain.ERROR;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iverno.gus.commons.general.application.bo.TransactionTypeDomain;
import com.iverno.gus.commons.general.application.dto.AccountDTO;
import com.iverno.gus.commons.general.application.exception.BaseException;
import static com.iverno.gus.config.Constants.*;

import com.iverno.gus.transaction.application.openfeign.AccountOpenFeignServiceImpl;
import com.iverno.gus.transaction.domain.entity.TransactionEntity;

@Component
public class TransactionValidate {
	@Autowired
	AccountOpenFeignServiceImpl accountOpenFeignServiceImpl;
	public BaseException validateBeforeSave(TransactionEntity entity) {
		try {
			boolean isWithdraw = entity.getTransactionType().equalsIgnoreCase(TransactionTypeDomain.WITHDRAW);
			Exception exception = new Exception();
			if (entity.getAccountId() == null || entity.getAccountId().isEmpty()) {
				return new BaseException().builder()
						.status(ERROR)
						.message("Cuenta bancaria no válida")
						.module(MODULE_TRASACTION)
						.exception(exception)
						.build();
			}
			
			AccountDTO accountDTO = accountOpenFeignServiceImpl.getAccountById(entity.getAccountId());
			
			if (accountDTO == null) {
				return new BaseException().builder()
										.status(ERROR)
										.message("Cuenta bancaria no válida")
										.module(MODULE_TRASACTION)
										.exception(exception)
										.build();
			}
			if (entity.getValue() == 0) {
				return new BaseException().builder()
										.status(ERROR)
										.message("Valor del movimiento inválida")
										.module(MODULE_TRASACTION)
										.exception(exception)
										.build();
			}
			if (accountDTO.getDailyWithdrawalLimit() == 0 && isWithdraw) {
				return new BaseException().builder()
										.status(ERROR)
										.message("Saldo no disponible")
										.module(MODULE_TRASACTION)
										.exception(exception)
										.build();
			}
			
			if (accountDTO.getDailyWithdrawalLimit() < ((-1)*entity.getValue()) && isWithdraw) {
				return new BaseException().builder()
										.status(ERROR)
										.message("Cupo diario Excedido")
										.module(MODULE_TRASACTION)
										.exception(exception)
										.build();
			}
			
			double availableBalance = accountDTO.getAvailableBalance() + entity.getValue();
			if (availableBalance <= 0 && isWithdraw) {
				return new BaseException().builder()
										.status(ERROR)
										.message("Saldo insuficiente")
										.module(MODULE_TRASACTION)
										.exception(exception)
										.build();
			}
	       	return null;
		} catch (Exception e) {
			return new BaseException().builder()
									.status(ERROR)
									.message(UNEXPECTED_ERROR)
									.module(MODULE_TRASACTION)
									.exception(e)
									.build();
		}
		
	}
}

