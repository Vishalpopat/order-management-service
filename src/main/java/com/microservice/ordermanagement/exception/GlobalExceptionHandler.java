package com.microservice.ordermanagement.exception;

import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import feign.FeignException;


@ControllerAdvice
public class GlobalExceptionHandler{
	
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ApiError> handleMethodArgumentNotValid(final CustomException customException){
		
		ApiError apiError = new ApiError(customException.getHttpStatus(), customException.getErrMsg(), MDC.get("X-B3-TraceId"), customException.getErrorCode(), null);
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<ApiError>(apiError, customException.getHttpStatus());
	}
	

//    @ExceptionHandler(FeignException.class)
//    public HttpServletResponse handleFeignStatusException(FeignException e, HttpServletResponse response) {
//        response.setStatus(e.status());
//        try {
//			response.getOutputStream().write(e.content());
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//        System.out.println("abab\n\n"+e.status()+"\n\n\n\n");
//        return response;
//    }
	
    
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiError> handleException(final Exception exception){
		
		ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, exception.getLocalizedMessage(), MDC.get("X-B3-TraceId"), UUID.randomUUID().toString(), null);
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<ApiError>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
