package com.openLegacydeloitte.com.exceptions;

public class CustomerException extends Exception {

	private static final long serialVersionUID = 7643746658925741427L;

	public CustomerException() {
		super();
	}

	public CustomerException(String message, Throwable cause) {
		super(message, cause);
	}

	public CustomerException(String message) {
		super(message);
	}

	public CustomerException(Throwable cause) {
		super(cause);
	}

}
