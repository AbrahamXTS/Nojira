package com.dinamitaexplosivainsana.nojira.domain.validators;

import com.dinamitaexplosivainsana.nojira.domain.dto.CreateProjectDTO;
import com.dinamitaexplosivainsana.nojira.domain.exceptions.RequiredArgumentException;

public class CreateProjectValidator {

    /**
     * Verify that the project always has a name, otherwise an exception is thrown.
     * @param project The record that defines the project.
     * @throws RequiredArgumentException
     */
    public static void validate(final CreateProjectDTO project) throws RequiredArgumentException {
        if (project.projectName().isEmpty()) {
            throw new RequiredArgumentException("Hey! El proyecto debe tener un nombre.");
        }
    }
}
