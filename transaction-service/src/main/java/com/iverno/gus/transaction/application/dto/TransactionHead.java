package com.iverno.gus.transaction.application.dto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import com.iverno.gus.commons.general.application.bo.AccountTypeDomain;
import com.iverno.gus.commons.general.util.Util;

public class TransactionHead {
	public enum exportHeaderEmun {
		DATE("Fecha"), 
		CUSTOMER("Cliente"),
		ACCOUNT_NUM("Numero Cuenta"),
		ACCOUNT_TYPE("Tipo Cuenta"),
		INITIAL_BALANCE("Saldo Inicial"),
		STATUS("Estado"),
		VALUE("Movimiento"),
		AVAILABLE_BALAMCE("Saldo Disponible");
		
		
		private String title;

		private exportHeaderEmun(String title) {
			this.title = title;
		}

		@Override
		public String toString() {
			return this.title;
		}

		public static String[] titles() {
			return Stream.of(exportHeaderEmun.values()).map(exportHeaderEmun::toString).toArray(String[]::new);
		}

		public static List<exportHeaderEmun> toList() {
			return Arrays.asList(exportHeaderEmun.values());
		}

		public String getExportValue(exportHeaderEmun header, TransactionDTO transactionDTO) {
			String newValue = Util.StringEmpty();

			switch (header) {
			case DATE:
				newValue = transactionDTO.getTransactionDate();
				break;

			case CUSTOMER:
				newValue = transactionDTO.getCustomerName();
				break;
				
			case ACCOUNT_NUM:
				newValue = transactionDTO.getAccountNum();
				break;

			case ACCOUNT_TYPE:
				newValue = transactionDTO.getAccountType().equalsIgnoreCase(AccountTypeDomain.SAVINGS_BANK_ACCOUNT)? "Cuenta de Ahorros":"Cuenta Corriente";
				break;
				
			case INITIAL_BALANCE:
				newValue = transactionDTO.getInitialBalance()+"";
				break;
			
			case STATUS:
				newValue = transactionDTO.isStatus()? "True":"False";
				break;
				
			case VALUE:
				newValue = transactionDTO.getValue()+"";
				break;

			case AVAILABLE_BALAMCE:
				newValue = transactionDTO.getAvailableBalance()+"";
				break;
			
			

			}

			return newValue;

		}

	}

	public enum exportNameMappingEmun {
		DATE("transactionDate"), 
		CUSTOMER("customerName"),
		ACCOUNT_NUM("accountNum"),
		ACCOUNT_TYPE("accountType"),
		INITIAL_BALANCE("initialBalance"),
		STATUS("status"),
		VALUE("value"),
		AVAILABLE_BALAMCE("avaulableBalance");
		 
		

		private String nameMapping;

		private exportNameMappingEmun(String nameMapping) {
			this.nameMapping = nameMapping;
		}

		@Override
		public String toString() {
			return this.nameMapping;
		}

		public static List<exportNameMappingEmun> toList() {
			return Arrays.asList(exportNameMappingEmun.values());
		}

		public static String[] nameMappings() {
			return Stream.of(exportNameMappingEmun.values()).map(exportNameMappingEmun::toString)
					.toArray(String[]::new);
		}
	}

}
