package com.iverno.gus.userservice.customer.application.bo;

import com.iverno.gus.userservice.person.application.bo.PersonBO;

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
}
