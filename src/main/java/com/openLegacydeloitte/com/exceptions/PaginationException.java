package com.openLegacydeloitte.com.exceptions;

public class PaginationException extends IllegalArgumentCustomerException {

	private static final long serialVersionUID = 7319004995865373541L;

	public PaginationException() {
		super();
	}

	public PaginationException(String message, Throwable cause) {
		super(message, cause);
	}

	public PaginationException(String message) {
		super(message);
	}

	public PaginationException(Throwable cause) {
		super(cause);
	}

}
