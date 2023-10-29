package com.dinamitaexplosivainsana.nojira.domain.validators;

import com.dinamitaexplosivainsana.nojira.domain.dto.UserSignupDTO;
import com.dinamitaexplosivainsana.nojira.domain.exceptions.RequiredArgumentException;
import com.dinamitaexplosivainsana.nojira.domain.exceptions.InvalidEmailFormatException;
import com.dinamitaexplosivainsana.nojira.domain.exceptions.InvalidFullNameFormatException;

import static com.dinamitaexplosivainsana.nojira.domain.config.Constants.*;

public class UserSignupValidator {

	private UserSignupValidator() {
	}

	public static void validate(final UserSignupDTO user) throws RequiredArgumentException, InvalidEmailFormatException, InvalidFullNameFormatException {
		if (user.fullName().isEmpty() || user.email().isEmpty() || user.password().isEmpty()) {
			throw new RequiredArgumentException(REQUIRED_ARGUMENT_EXCEPTION_MESSAGE);
		}

		if (!isFullNameFormatValid(user.fullName())) {
			throw new InvalidFullNameFormatException(INVALID_FULL_NAME_FORMAT_EXCEPTION_MESSAGE);
		}

		if (!isEmailFormatValid(user.email())) {
			throw new InvalidEmailFormatException(INVALID_EMAIL_FORMAT_EXCEPTION_MESSAGE);
		}
	}

	private static boolean isEmailFormatValid(String email) {
		String emailPattern = "^[\\w\\.-]+@[\\w\\.-]+\\.\\w+";
		return email.matches(emailPattern);
	}

	private static boolean isFullNameFormatValid(String fullName) {
		String fullNamePattern = "^[\\p{L} ]+$";
		return fullName.matches(fullNamePattern);
	}
}
