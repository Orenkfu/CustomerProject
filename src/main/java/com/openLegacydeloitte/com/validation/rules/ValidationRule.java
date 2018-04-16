package com.openLegacydeloitte.com.validation.rules;

import com.openLegacydeloitte.com.exceptions.ValidationException;
import com.openLegacydeloitte.com.validation.ValidationData;

public interface ValidationRule {

	void validate(ValidationData data) throws ValidationException;
}
