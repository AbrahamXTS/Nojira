package com.dinamitaexplosivainsana.nojira.domain.validators;

import com.dinamitaexplosivainsana.nojira.domain.dto.TaskCreateDTO;
import com.dinamitaexplosivainsana.nojira.domain.exceptions.RequiredArgumentException;

import static com.dinamitaexplosivainsana.nojira.domain.config.Constants.REQUIRED_ARGUMENT_EXCEPTION_MESSAGE;

public class TaskCreateValidator {

    public static void validate(TaskCreateDTO taskToCreate){
        if(taskToCreate.title().isEmpty() || taskToCreate.description().isEmpty()){
            throw new RequiredArgumentException(REQUIRED_ARGUMENT_EXCEPTION_MESSAGE);
        }
    }
}
