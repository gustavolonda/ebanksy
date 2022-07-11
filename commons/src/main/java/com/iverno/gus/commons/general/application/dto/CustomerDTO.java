package com.iverno.gus.commons.general.application.dto;
import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
	private String id;
	private String name;
	private String address;
	private String password;
	private String gender;
	private int age;
	private String idCard;
	private String phone;
	private boolean status;
}
