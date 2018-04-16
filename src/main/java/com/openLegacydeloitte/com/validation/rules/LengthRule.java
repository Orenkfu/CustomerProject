package com.openLegacydeloitte.com.validation.rules;

import com.openLegacydeloitte.com.exceptions.ValidationException;
import com.openLegacydeloitte.com.validation.ValidationData;

/**
 * Rule maintaining class responsible for making sure all data sent to Database
 * does not exceed Database character limit.
 */
public class LengthRule implements ValidationRule {

	private static final int LENGTH_CONSTRAINT = 255;

	/**
	 * Validates no fields exceed database character length limit.
	 * 
	 * @throws ValidationException
	 *             if a field exceeds limit.
	 * 
	 * @see com.openLegacydeloitte.com.validation.rules.ValidationRule#validate(com.openLegacydeloitte.com.validation.ValidationData)
	 */
	@Override
	public void validate(ValidationData data) throws ValidationException {

		if (data.getName().length() > LENGTH_CONSTRAINT) {
			throw new ValidationException("Name cannot exceed " + LENGTH_CONSTRAINT + " characters.");
		} else if (data.getAddress().length() > LENGTH_CONSTRAINT) {
			throw new ValidationException("Address cannot exceed " + LENGTH_CONSTRAINT + " characters.");
		} else if (data.getPhoneNumber().length() > LENGTH_CONSTRAINT) {
			throw new ValidationException("Phone number cannot exceed " + LENGTH_CONSTRAINT + " characters.");
		}
	}

}
