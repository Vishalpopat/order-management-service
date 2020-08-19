package com.microservice.ordermanagement.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.microservice.ordermanagement.exception.ErrorEnum;
import com.microservice.ordermanagement.model.Order;

@Component
public class OrderValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Order.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Order order = (Order) target;
		if (checkInputString(order.getCustomerName())) {
			errors.rejectValue("customerName", ErrorEnum.VALIDATION_ERROR_INVALID_FIELD.getErrCode(),
					ErrorEnum.VALIDATION_ERROR_INVALID_FIELD.getErrMsg());
		}
		if (checkInputString(order.getAddress())) {
			errors.rejectValue("address", ErrorEnum.VALIDATION_ERROR_INVALID_FIELD.getErrCode(),
					ErrorEnum.VALIDATION_ERROR_INVALID_FIELD.getErrMsg());
		}
		if (null == order.getOrderItems() || order.getOrderItems().isEmpty()) {
			errors.rejectValue("orderItems", ErrorEnum.VALIDATION_ERROR_EMPTY_ORDER_ITEMS.getErrCode(),
					ErrorEnum.VALIDATION_ERROR_EMPTY_ORDER_ITEMS.getErrMsg());
		}
		if (order.getId() != null) {
			errors.rejectValue("id", ErrorEnum.VALIDATION_ERROR_FIELD_NOT_ALLOWED.getErrCode(),
					ErrorEnum.VALIDATION_ERROR_FIELD_NOT_ALLOWED.getErrMsg());
		}

	}

	private boolean checkInputString(String input) {
		return (input == null || input.trim().length() == 0);
	}

}
