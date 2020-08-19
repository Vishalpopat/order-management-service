package com.microservice.ordermanagement.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.microservice.ordermanagement.exception.ErrorEnum;
import com.microservice.ordermanagement.model.Order;
import com.microservice.ordermanagement.model.OrderItem;

@Component
public class OrderItemValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return OrderItem.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		OrderItem orderItem = (OrderItem) target;
		if (checkInputString(orderItem.getName())) {
			errors.rejectValue("name", ErrorEnum.VALIDATION_ERROR_INVALID_FIELD.getErrCode(),
					ErrorEnum.VALIDATION_ERROR_INVALID_FIELD.getErrMsg());
		}

		if (null == orderItem.getQuantity() || orderItem.getQuantity() < 1) {
			errors.rejectValue("quantity", ErrorEnum.VALIDATION_ERROR_INVALID_FIELD.getErrCode(),
					ErrorEnum.VALIDATION_ERROR_INVALID_FIELD.getErrMsg());
		}
		if (orderItem.getCode() != null) {
			errors.rejectValue("code", ErrorEnum.VALIDATION_ERROR_FIELD_NOT_ALLOWED.getErrCode(),
					ErrorEnum.VALIDATION_ERROR_FIELD_NOT_ALLOWED.getErrMsg());
		}

	}

	private boolean checkInputString(String input) {
		return (input == null || input.trim().length() == 0);
	}

}
