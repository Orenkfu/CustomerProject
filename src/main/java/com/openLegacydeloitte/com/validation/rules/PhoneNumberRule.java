package com.openLegacydeloitte.com.validation.rules;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.openLegacydeloitte.com.exceptions.ValidationException;
import com.openLegacydeloitte.com.validation.ValidationData;

public class PhoneNumberRule implements ValidationRule {

	@Override
	public void validate(ValidationData data) throws ValidationException {
		String pattern = "^((\\+|00)?972\\-?|0)(([23489]|[57]\\d)\\-?\\d{7})$";
		Matcher m = Pattern.compile(pattern).matcher(data.getPhoneNumber());
		if (!m.matches()) {
			throw new ValidationException(
					"Please enter a valid phone number (currently only recognizing Israeli numbers).");
		}
	}
}