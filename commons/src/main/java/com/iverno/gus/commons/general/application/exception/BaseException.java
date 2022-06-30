package com.iverno.gus.commons.general.application.exception;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseException extends Exception {
	private String status;
	private String message;
	private String module;
	private Exception exception;
}