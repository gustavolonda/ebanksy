package com.iverno.gus.userservice.person.application.adapter;





import java.util.List;
import java.util.stream.Collectors;

import com.iverno.gus.userservice.person.application.bo.PersonBO;
import com.iverno.gus.userservice.person.domain.entities.PersonEntity;


public class PersonAdapter {

	public static List<PersonBO> personEntityListToPersonBOList(List<PersonEntity> entityList) {
		return entityList.stream().map(p -> personEntityToPersonBO(p))
									.collect(Collectors.toList());
	}

	public static PersonBO personEntityToPersonBO(PersonEntity entity) {
		return new PersonBO().builder().id(entity.getId())
										.name(entity.getName())
										.gender(entity.getGender())
										.age(entity.getAge())
										.idCard(entity.getIdCard())
										.address(entity.getAddress())
										.phone(entity.getPhone())
										.build();
	}

	public static PersonEntity personBOToPersonEntity(PersonBO request) {
		
		return new PersonEntity().builder()
								.id(request.getId())
								.name(request.getName())
								.gender(request.getGender())
								.age(request.getAge())
								.idCard(request.getIdCard())
								.address(request.getAddress())
								.phone(request.getPhone())
								.build();
	}

}
