package com.openLegacydeloitte.com.exceptions;

public class NotFoundException extends CustomerException {

	private static final long serialVersionUID = 314951195154171412L;

	public NotFoundException() {
		super();
	}

	public NotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotFoundException(String message) {
		super(message);
	}

	public NotFoundException(Throwable cause) {
		super(cause);
	}

}
