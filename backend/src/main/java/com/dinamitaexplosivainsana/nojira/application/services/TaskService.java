package com.dinamitaexplosivainsana.nojira.application.services;

import com.dinamitaexplosivainsana.nojira.application.repositories.RoleRepository;
import com.dinamitaexplosivainsana.nojira.application.repositories.TaskRepository;
import com.dinamitaexplosivainsana.nojira.domain.dto.SuccessfulCreatedTaskDTO;
import com.dinamitaexplosivainsana.nojira.domain.dto.TaskAssignedToDTO;
import com.dinamitaexplosivainsana.nojira.domain.dto.TaskCreateDTO;
import com.dinamitaexplosivainsana.nojira.domain.dto.TaskTimesDTO;
import com.dinamitaexplosivainsana.nojira.domain.exceptions.UnauthorizedUserException;
import com.dinamitaexplosivainsana.nojira.domain.models.*;
import com.dinamitaexplosivainsana.nojira.domain.validators.TaskCreateValidator;

import java.util.Objects;

import static com.dinamitaexplosivainsana.nojira.domain.config.Constants.NOT_AUTHORIZED_TO_CREATE_TASK_EXCEPTION_MESSAGE;
import static com.dinamitaexplosivainsana.nojira.domain.config.Constants.UNRELATED_USER_IN_PROJECT_EXCEPTION_MESSAGE;

public class TaskService {
    private final TaskRepository taskRepository;
    private final RoleRepository roleRepository;

    public TaskService(TaskRepository taskRepository,
                       RoleRepository roleRepository) {
        this.taskRepository = taskRepository;
        this.roleRepository = roleRepository;
    }

    public SuccessfulCreatedTaskDTO createTask(TaskCreateDTO task, String userId, String proyectId){
        TaskCreateValidator.validate(task);

        Role roleBetweenUserAndProject = this.roleRepository.findRoleBetweenUserAndProject(userId, proyectId);
        Integer proyectOwner = 1;

        if(Objects.isNull(roleBetweenUserAndProject)){
            throw new UnauthorizedUserException(UNRELATED_USER_IN_PROJECT_EXCEPTION_MESSAGE);
        } else if(!Objects.equals(roleBetweenUserAndProject.roleCatalog().id(), proyectOwner)){
            throw new UnauthorizedUserException(NOT_AUTHORIZED_TO_CREATE_TASK_EXCEPTION_MESSAGE);
        }

        User userAssigned = roleBetweenUserAndProject.user();
        Project projectBelonging = roleBetweenUserAndProject.project();

        Task taskSaved = this.taskRepository.saveTask(
                new Task(
                        null,
                        task.description(),
                        0,
                        task.title(),
                        0,
                        userAssigned,
                        projectBelonging,
                        null
                )
        );

        return new SuccessfulCreatedTaskDTO(
                taskSaved.id(),
                taskSaved.title(),
                taskSaved.description(),
                taskSaved.status().type(),
                new TaskTimesDTO(
                        taskSaved.timeEstimatedInMinutes(),
                        taskSaved.timeUsedInMinutes()
                ),
                new TaskAssignedToDTO(
                        taskSaved.userAsigned().id(),
                        taskSaved.userAsigned().fullName()
                )
        );
    }
}
