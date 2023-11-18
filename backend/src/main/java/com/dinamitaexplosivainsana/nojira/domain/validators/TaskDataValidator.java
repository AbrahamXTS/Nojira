package com.dinamitaexplosivainsana.nojira.domain.validators;

import com.dinamitaexplosivainsana.nojira.domain.dto.TaskDTO;
import com.dinamitaexplosivainsana.nojira.domain.exceptions.RequiredArgumentException;
import static com.dinamitaexplosivainsana.nojira.domain.config.Constants.REQUIRED_ARGUMENT_EXCEPTION_MESSAGE;



public class TaskDataValidator {
    public static void validateTaskDataIsNotEmpty(TaskDTO updateTask){
        if (updateTask.title().isEmpty() || updateTask.description().isEmpty()) {
            throw new RequiredArgumentException(REQUIRED_ARGUMENT_EXCEPTION_MESSAGE);
        }
    }
}
