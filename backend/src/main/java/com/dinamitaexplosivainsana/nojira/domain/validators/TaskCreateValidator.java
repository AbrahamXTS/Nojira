package com.dinamitaexplosivainsana.nojira.domain.validators;

import com.dinamitaexplosivainsana.nojira.domain.dto.TaskCreateDTO;
import com.dinamitaexplosivainsana.nojira.domain.exceptions.RequiredArgumentException;
import com.dinamitaexplosivainsana.nojira.domain.exceptions.UnauthorizedUserException;
import com.dinamitaexplosivainsana.nojira.domain.models.Role;

import java.util.Objects;

import static com.dinamitaexplosivainsana.nojira.domain.config.Constants.*;

public class TaskCreateValidator {

    public static void validate(TaskCreateDTO taskToCreate, Role roleBetweenUserAndProject){
        if(taskToCreate.title().isEmpty() || taskToCreate.description().isEmpty()){
            throw new RequiredArgumentException(REQUIRED_ARGUMENT_EXCEPTION_MESSAGE);
        } else if(roleBetweenUserAndProject.roleCatalog().id() != 1){
            throw new UnauthorizedUserException(NOT_AUTHORIZED_TO_CREATE_TASK_EXCEPTION_MESSAGE);
        }
    }
}
