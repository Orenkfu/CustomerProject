package com.openLegacydeloitte.com.exceptions;

public class IllegalArgumentCustomerException extends CustomerException {

	private static final long serialVersionUID = -6969063346535577413L;

	public IllegalArgumentCustomerException() {
		super();
	}

	public IllegalArgumentCustomerException(String message, Throwable cause) {
		super(message, cause);
	}

	public IllegalArgumentCustomerException(String message) {
		super(message);
	}

	public IllegalArgumentCustomerException(Throwable cause) {
		super(cause);
	}

}
