package com.openLegacydeloitte.com.validation.rules;

import com.openLegacydeloitte.com.exceptions.ValidationException;
import com.openLegacydeloitte.com.validation.ValidationData;

/**
 * Rule maintaining class responsible for making sure all Customer data sent to
 * Database is not null/empty whitespace. fields.
 *
 */
public class EmptyFieldRule implements ValidationRule {
	/**
	 * Validates null/empty fields.
	 * 
	 * @throws ValidationException
	 *             if a field is either null or empty string.
	 * 
	 * @see com.openLegacydeloitte.com.validation.rules.ValidationRule#validate(com.openLegacydeloitte.com.validation.ValidationData)
	 */
	@Override
	public void validate(ValidationData data) throws ValidationException {
		if (data == null) {
			throw new ValidationException("Request failed, Cannot accept empty fields.");
		} else if (data.getName() == null | data.getName().isEmpty()) {
			throw new ValidationException("Name field must not be empty.");
		} else if (data.getAddress() == null | data.getAddress().trim().isEmpty()) {
			throw new ValidationException("Address field must not be empty.");
		} else if (data.getPhoneNumber() == null | data.getPhoneNumber().trim().isEmpty()) {
			throw new ValidationException("Phone Number field must not be empty.");

		}

	}
}