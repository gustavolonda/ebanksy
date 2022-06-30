package com.iverno.gus.userservice.person.application.bo;

import com.iverno.gus.userservice.person.application.adapter.PersonAdapter;

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
