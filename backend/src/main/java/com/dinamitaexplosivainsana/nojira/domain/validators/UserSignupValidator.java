package com.dinamitaexplosivainsana.nojira.domain.validators;

import com.dinamitaexplosivainsana.nojira.domain.dto.UserSignupDTO;
import com.dinamitaexplosivainsana.nojira.domain.exceptions.InvalidArgumentException;

import static com.dinamitaexplosivainsana.nojira.domain.config.Constants.*;
/**
 * This class provides utility methods for validating user signup data.
 * It is not meant to be instantiated, hence the private constructor.
 */
public class UserSignupValidator {

    private UserSignupValidator() {
    }
     /**
     * Validates that the provided user signup data is not empty and follows the correct format.
     *
     * @param user The user signup data transfer object.
     * @throws InvalidArgumentException If the user signup data is empty or does not follow the correct format.
     */
    public static void validate(final UserSignupDTO user) throws InvalidArgumentException {
        if (user.fullName().isEmpty() || user.email().isEmpty() || user.password().isEmpty()) {
            throw new InvalidArgumentException(REQUIRED_ARGUMENT_EXCEPTION_MESSAGE);
        }

        if (!isFullNameFormatValid(user.fullName())) {
            throw new InvalidArgumentException(INVALID_FULL_NAME_FORMAT_EXCEPTION_MESSAGE);
        }

        if (!isEmailFormatValid(user.email())) {
            throw new InvalidArgumentException(INVALID_EMAIL_FORMAT_EXCEPTION_MESSAGE);
        }
    }
/**
     * Checks if the provided email follows the correct format.
     *
     * @param email The email to be checked.
     * @return true if the email follows the correct format, false otherwise.
     */
    private static boolean isEmailFormatValid(String email) {
        String emailPattern = "^[\\w\\.-]+@[\\w\\.-]+\\.\\w+";
        return email.matches(emailPattern);
    }
/**
     * Checks if the provided full name follows the correct format.
     *
     * @param fullName The full name to be checked.
     * @return true if the full name follows the correct format, false otherwise.
     */
    private static boolean isFullNameFormatValid(String fullName) {
        String fullNamePattern = "^[\\p{L} ]+$";
        return fullName.matches(fullNamePattern);
    }
}
