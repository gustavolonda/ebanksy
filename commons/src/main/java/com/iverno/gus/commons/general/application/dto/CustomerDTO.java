package com.iverno.gus.commons.general.application.dto;
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
	private boolean status;
}
