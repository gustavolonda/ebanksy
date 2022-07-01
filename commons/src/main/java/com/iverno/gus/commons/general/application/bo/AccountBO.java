package com.iverno.gus.commons.general.application.bo;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountBO {
	private String id;
	private CustomerBO customerBO;
	private String accountNum;
	private String accountType;
	private String initialBalance;
}
