package com.iverno.gus.commons.general.application.bo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerBO {
	private String id;
	private PersonBO personBO; 
	private String password;
	private boolean status;
}
