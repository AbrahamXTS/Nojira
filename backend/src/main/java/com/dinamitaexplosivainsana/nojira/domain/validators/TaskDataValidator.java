package com.dinamitaexplosivainsana.nojira.domain.validators;

import com.dinamitaexplosivainsana.nojira.domain.exceptions.InvalidArgumentException;

import static com.dinamitaexplosivainsana.nojira.domain.config.Constants.REQUIRED_ARGUMENT_EXCEPTION_MESSAGE;

public class TaskDataValidator {
    private TaskDataValidator() {
    }

    public static void validateTaskDataIsNotEmpty(String title, String description) {
        if (title.isEmpty() || description.isEmpty()) {
            throw new InvalidArgumentException(REQUIRED_ARGUMENT_EXCEPTION_MESSAGE);
        }
    }
}
