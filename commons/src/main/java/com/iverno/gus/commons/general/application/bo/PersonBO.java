package com.iverno.gus.commons.general.application.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonBO {
	private String id;
	private String name;
	private String gender;
	private int age;
	private String idCard;
	private String address;
	private String phone;
}
