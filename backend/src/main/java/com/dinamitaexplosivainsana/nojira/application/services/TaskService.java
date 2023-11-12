package com.dinamitaexplosivainsana.nojira.application.services;

import com.dinamitaexplosivainsana.nojira.application.repositories.StatusRepository;
import com.dinamitaexplosivainsana.nojira.application.repositories.TaskRepository;
import com.dinamitaexplosivainsana.nojira.application.repositories.UserRepository;
import com.dinamitaexplosivainsana.nojira.domain.dto.SuccessfulCreatedTaskDTO;
import com.dinamitaexplosivainsana.nojira.domain.dto.TaskAssignedToDTO;
import com.dinamitaexplosivainsana.nojira.domain.dto.TaskCreateDTO;
import com.dinamitaexplosivainsana.nojira.domain.dto.TaskTimesDTO;
import com.dinamitaexplosivainsana.nojira.domain.exceptions.StatusNotFoundException;
import com.dinamitaexplosivainsana.nojira.domain.models.Status;
import com.dinamitaexplosivainsana.nojira.domain.models.Task;
import com.dinamitaexplosivainsana.nojira.domain.models.User;
import com.dinamitaexplosivainsana.nojira.domain.validators.TaskCreateValidator;

import java.util.Objects;

import static com.dinamitaexplosivainsana.nojira.domain.config.Constants.STATUS_OF_TASK_NOT_FOUND_EXCEPTION_MESSAGE;

public class TaskService {
    private final TaskRepository taskRepository;
    private final StatusRepository statusRepository;

    private final UserRepository userRepository;


    public TaskService(TaskRepository taskRepository,
                       StatusRepository statusRepository,
                       UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.statusRepository = statusRepository;
        this.userRepository = userRepository;
    }

    public SuccessfulCreatedTaskDTO createTask(TaskCreateDTO task, String userId, String proyectId){
        TaskCreateValidator.validate(task);

        String taskStatusId = "1";
        //Status taskStatus = this.statusRepository.getStatusByType(taskStatusType);

        /*
        if (Objects.isNull(taskStatus)){
            throw new StatusNotFoundException(STATUS_OF_TASK_NOT_FOUND_EXCEPTION_MESSAGE);
        }
        */

        Task taskSaved = this.taskRepository.saveTask(
                new Task(
                        null,
                        task.description(),
                        0,
                        task.title(),
                        0,
                        userId,
                        proyectId,
                        //taskStatus.type()
                        taskStatusId
                )
        );

        User taskUser = this.userRepository.getUserByUserId(userId);
        return new SuccessfulCreatedTaskDTO(
                taskSaved.id(),
                taskSaved.title(),
                taskSaved.description(),
                //taskStatus.type(),
                "Por hacer",
                new TaskTimesDTO(
                        taskSaved.timeEstimatedInMinutes(),
                        taskSaved.timeUsedInMinutes()
                ),
                new TaskAssignedToDTO(
                        taskUser.id(),
                        taskUser.fullName()
                )
        );
    }
}
