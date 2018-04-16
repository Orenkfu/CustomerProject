package com.openLegacydeloitte.com.exceptions;

public class ValidationException extends IllegalArgumentCustomerException {

	private static final long serialVersionUID = -4610308044952143213L;

	public ValidationException() {
		super();
	}

	public ValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ValidationException(String message) {
		super(message);
	}

	public ValidationException(Throwable cause) {
		super(cause);
	}

}
