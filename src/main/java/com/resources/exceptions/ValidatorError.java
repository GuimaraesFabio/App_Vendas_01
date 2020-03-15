package com.resources.exceptions;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidatorError extends StandardError {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<FieldMessage> errors = new ArrayList<>();

	public ValidatorError() {
	}

	public ValidatorError(Instant timeStamp, Integer status, String error, String message, String path) {
		super(timeStamp, status, error, message, path);
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addErrors(String fieldName, String messageError) {
		errors.add(new FieldMessage(fieldName, messageError));
	}

}
