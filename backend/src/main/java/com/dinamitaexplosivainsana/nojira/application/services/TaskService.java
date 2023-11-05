package com.dinamitaexplosivainsana.nojira.application.services;

import com.dinamitaexplosivainsana.nojira.application.repositories.StatusRepository;
import com.dinamitaexplosivainsana.nojira.application.repositories.TaskDBRepository;
import com.dinamitaexplosivainsana.nojira.application.repositories.TaskRepository;
import com.dinamitaexplosivainsana.nojira.application.repositories.UserRepository;
import com.dinamitaexplosivainsana.nojira.application.utils.JWTUtils;
import com.dinamitaexplosivainsana.nojira.domain.dto.SuccessfulCreatedTaskDTO;
import com.dinamitaexplosivainsana.nojira.domain.dto.TaskAssignedToDTO;
import com.dinamitaexplosivainsana.nojira.domain.dto.TaskCreateDTO;
import com.dinamitaexplosivainsana.nojira.domain.dto.TaskTimesDTO;
import com.dinamitaexplosivainsana.nojira.domain.models.Status;
import com.dinamitaexplosivainsana.nojira.domain.models.TaskDB;
import com.dinamitaexplosivainsana.nojira.domain.models.User;
import com.dinamitaexplosivainsana.nojira.domain.validators.TaskCreateValidator;
import com.dinamitaexplosivainsana.nojira.infrastructure.schemas.ProjectSchema;
import com.dinamitaexplosivainsana.nojira.infrastructure.schemas.StatusCatalogSchema;
import com.dinamitaexplosivainsana.nojira.infrastructure.schemas.UserSchema;

import javax.xml.validation.Validator;

public class TaskService {
    private final TaskDBRepository taskRepository;
    private final StatusRepository statusRepository;
    private final UserRepository userRepository;


    public TaskService(TaskDBRepository taskRepository, StatusRepository statusRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.statusRepository = statusRepository;
        this.userRepository = userRepository;
    }

    public SuccessfulCreatedTaskDTO saveTask(TaskCreateDTO task, String idUser, String idProyect){
        TaskCreateValidator.validate(task);

        String typeStatus = "Por hacer";
        Status status = this.statusRepository.getStatusByType(typeStatus);

        TaskDB taskDB = this.taskRepository.saveTask(
                new TaskDB(
                        null,
                        task.title(),
                        task.description(),
                        0,
                        0,
                        ProjectSchema
                                .builder()
                                .id(idProyect)
                                .build(),
                        UserSchema
                                .builder()
                                .id(idUser)
                                .build(),
                        StatusCatalogSchema
                                .builder()
                                .id(status.id())
                                .type(status.type())
                                .build()
                )
        );

        User user = this.userRepository.getUserByUserId(idUser);
        return new SuccessfulCreatedTaskDTO(
                taskDB.id(),
                taskDB.title(),
                taskDB.description(),
                taskDB.status().getType(),
                new TaskTimesDTO(
                        taskDB.estimated(),
                        taskDB.total()
                ),
                new TaskAssignedToDTO(
                        user.id(),
                        user.fullName()
                )
        );
    }
}
