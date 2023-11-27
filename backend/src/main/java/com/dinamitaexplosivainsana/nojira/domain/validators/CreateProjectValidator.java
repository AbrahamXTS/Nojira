package com.dinamitaexplosivainsana.nojira.domain.validators;

import com.dinamitaexplosivainsana.nojira.domain.dto.CreateProjectDTO;
import com.dinamitaexplosivainsana.nojira.domain.exceptions.InvalidArgumentException;

import static com.dinamitaexplosivainsana.nojira.domain.config.Constants.REQUIRED_ARGUMENT_EXCEPTION_MESSAGE;

public class CreateProjectValidator {
    private CreateProjectValidator() {
    }

    /**
     * Verify that the project always has a name, otherwise an exception is thrown.
     *
     * @param project The record that defines the project.
     */
    public static void validate(final CreateProjectDTO project) throws InvalidArgumentException {
        if (project.title().isEmpty() || project.description().isEmpty()) {
            throw new InvalidArgumentException(REQUIRED_ARGUMENT_EXCEPTION_MESSAGE);
        }
    }
}
