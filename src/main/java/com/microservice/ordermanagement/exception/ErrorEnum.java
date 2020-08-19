package com.microservice.ordermanagement.exception;
import org.springframework.http.HttpStatus;
public enum ErrorEnum {
	
	GENERIC_ERROR("Error.operationFailedToComplete","The system is unable to complete the task. Contact your supervisor.", HttpStatus.INTERNAL_SERVER_ERROR),
	VALIDATION_ERROR_INVALID_FIELD("Error.invalidFiledValue", "Field contains invalid value.", HttpStatus.BAD_REQUEST),
	VALIDATION_ERROR_EMPTY_ORDER_ITEMS("Error.emptyOrder", "Please add order items.", HttpStatus.BAD_REQUEST),
	VALIDATION_ERROR_FIELD_NOT_ALLOWED("Error.fieldNotAllowed", "Please remove field from request.", HttpStatus.BAD_REQUEST),
	ORDER_NOT_FOUND("Error.orderNotFound", "Order not found.", HttpStatus.NOT_FOUND),
	ORDER_ITEM_NOT_FOUND("Error.orderItemNotFound", "Order Item not found.", HttpStatus.NOT_FOUND);
	

	
	private String errCode;
	private String errMsg;
	private HttpStatus httpStatus;

	ErrorEnum(final String errCode, final String errMsg,final HttpStatus httpStatus) {
		this.errCode = errCode;
		this.errMsg = errMsg;
		this.httpStatus = httpStatus;
	}

	public String getErrCode() {
		return errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}
	public HttpStatus getStatusCode() {
		return httpStatus;
	}


}
