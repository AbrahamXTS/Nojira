package com.dinamitaexplosivainsana.nojira.domain.validators;

import com.dinamitaexplosivainsana.nojira.domain.exceptions.InvalidArgumentException;

import static com.dinamitaexplosivainsana.nojira.domain.config.Constants.REQUIRED_ARGUMENT_EXCEPTION_MESSAGE;
/**
 * This class provides utility methods for validating task data.
 * It is not meant to be instantiated, hence the private constructor.
 */
public class TaskDataValidator {
    private TaskDataValidator() {
    }
/**
     * Validates that the provided task title and description are not empty.
     *
     * @param title       The title of the task.
     * @param description The description of the task.
     * @throws InvalidArgumentException If the title or description is empty.
     */
    public static void validateTaskDataIsNotEmpty(String title, String description) {
        if (title.isEmpty() || description.isEmpty()) {
            throw new InvalidArgumentException(REQUIRED_ARGUMENT_EXCEPTION_MESSAGE);
        }
    }
}
