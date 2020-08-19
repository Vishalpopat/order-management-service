package com.microservice.ordermanagement.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException {
	
	private final String errMsg;
	private final String errorCode;
	private final HttpStatus httpStatus;
	
	public CustomException(final ErrorEnum erorrInfo) {
		this.errorCode = erorrInfo.getErrCode();
		this.errMsg = erorrInfo.getErrMsg();
		this.httpStatus = erorrInfo.getStatusCode();
	}

	

}
