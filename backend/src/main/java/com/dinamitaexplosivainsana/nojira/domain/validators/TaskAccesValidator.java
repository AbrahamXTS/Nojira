package com.dinamitaexplosivainsana.nojira.domain.validators;

import static com.dinamitaexplosivainsana.nojira.domain.config.Constants.TASK_NOT_FOUND_EXCEPTION_MESSAGE;
import static com.dinamitaexplosivainsana.nojira.domain.config.Constants.TASK_NOT_FOUND_IN_PROJECT_EXCEPTION_MESSAGE;
import static com.dinamitaexplosivainsana.nojira.domain.config.Constants.UNAUTHORIZED_ACTION_TASK_EXCEPTION_MESSAGE;
import static com.dinamitaexplosivainsana.nojira.domain.config.Constants.UNRELATED_USER_IN_PROJECT_EXCEPTION_MESSAGE;

import java.util.Objects;

import com.dinamitaexplosivainsana.nojira.domain.exceptions.TaskNotFoundException;
import com.dinamitaexplosivainsana.nojira.domain.exceptions.UnauthorizedUserException;
import com.dinamitaexplosivainsana.nojira.domain.models.Project;
import com.dinamitaexplosivainsana.nojira.domain.models.Role;
import com.dinamitaexplosivainsana.nojira.domain.models.RoleCatalog;
import com.dinamitaexplosivainsana.nojira.domain.models.StatusCatalogEnum;
import com.dinamitaexplosivainsana.nojira.domain.models.Task;

public class TaskAccesValidator {

    public static void validateAcces(Role roleBetweenUserAndProject, Task task) {

        validateRelatedBetweenUserAndProject(roleBetweenUserAndProject); 
        validateUserRoleInProyect(roleBetweenUserAndProject.roleCatalog()); 
        validateTaskExistInProyect(roleBetweenUserAndProject.project(), task); 
    }

    private static void validateRelatedBetweenUserAndProject(Role roleBetweenUserAndProject){
        if (Objects.isNull(roleBetweenUserAndProject)) {
            throw new UnauthorizedUserException(UNRELATED_USER_IN_PROJECT_EXCEPTION_MESSAGE);
        }
    }

    private static void validateUserRoleInProyect(RoleCatalog userRole){
        if (!Objects.equals(userRole.id(), StatusCatalogEnum.TO_DO.getId())) {
            throw new UnauthorizedUserException(UNAUTHORIZED_ACTION_TASK_EXCEPTION_MESSAGE);
        }
    }

    private static void validateTaskExistInProyect(Project useProject, Task task){
        if (Objects.isNull(task)){
            throw new TaskNotFoundException(TASK_NOT_FOUND_EXCEPTION_MESSAGE);
        }

        if (!Objects.equals(useProject, task.projectBelonging())){
            throw new TaskNotFoundException(TASK_NOT_FOUND_IN_PROJECT_EXCEPTION_MESSAGE);
        }
    }
}
