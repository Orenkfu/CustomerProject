package com.openLegacydeloitte.com.validation;

import java.util.ArrayList;
import java.util.List;

import com.openLegacydeloitte.com.entities.Customer;
import com.openLegacydeloitte.com.exceptions.ValidationException;
import com.openLegacydeloitte.com.validation.rules.EmptyFieldRule;
import com.openLegacydeloitte.com.validation.rules.LengthRule;
import com.openLegacydeloitte.com.validation.rules.PhoneNumberRule;
import com.openLegacydeloitte.com.validation.rules.ValidationRule;

/**
 * Handler class for all validations.<br>
 * Constructs a list of validation checks to be called by Service (Facade)
 * methods.
 *
 */
public class ValidationChecklist {

	private static List<ValidationRule> customerChecklist() {
		List<ValidationRule> rules = new ArrayList<>();
		rules.add(new EmptyFieldRule());
		rules.add(new LengthRule());
		rules.add(new PhoneNumberRule());
		return rules;
	}

	/**
	 * Iterates over a "checklist" of validation rules. any new rule must be added
	 * to list.
	 * 
	 * @param customer
	 * @throws ValidationException
	 *             if a rule been broken.
	 */
	public static void validateFields(Customer customer) throws ValidationException {
		for (ValidationRule validationRule : ValidationChecklist.customerChecklist()) {
			validationRule.validate(new ValidationData(customer));
		}

	}
}
