package com.dinamitaexplosivainsana.nojira.domain.validators;

import com.dinamitaexplosivainsana.nojira.domain.dto.CreateProjectDTO;
import com.dinamitaexplosivainsana.nojira.domain.exceptions.RequiredArgumentException;

import static com.dinamitaexplosivainsana.nojira.domain.config.Constants.REQUIRED_ARGUMENT_EXCEPTION_MESSAGE;

public class ProjectValidator {

    /**
     * Verify that the project always has a name, otherwise an exception is thrown.
     * @param project The record that defines the project.
     * @throws RequiredArgumentException
     */
    public static void validate(final CreateProjectDTO project) throws RequiredArgumentException {
        if (project.projectName().isEmpty()) {
            throw new RequiredArgumentException(REQUIRED_ARGUMENT_EXCEPTION_MESSAGE);
        }
    }
}
