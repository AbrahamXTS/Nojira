package com.dinamitaexplosivainsana.nojira.application.services;

import com.dinamitaexplosivainsana.nojira.application.repositories.RoleRepository;
import com.dinamitaexplosivainsana.nojira.application.repositories.TaskRepository;
import com.dinamitaexplosivainsana.nojira.domain.dto.SuccessfulCreatedTaskDTO;
import com.dinamitaexplosivainsana.nojira.domain.dto.TaskAssignedToDTO;
import com.dinamitaexplosivainsana.nojira.domain.dto.TaskCreateDTO;
import com.dinamitaexplosivainsana.nojira.domain.dto.TaskTimesDTO;
import com.dinamitaexplosivainsana.nojira.domain.models.*;
import com.dinamitaexplosivainsana.nojira.domain.validators.TaskCreateValidator;

public class TaskService {
    private final TaskRepository taskRepository;
    private final RoleRepository roleRepository;


    public TaskService(TaskRepository taskRepository,
                       RoleRepository roleRepository) {
        this.taskRepository = taskRepository;
        this.roleRepository = roleRepository;
    }

    public SuccessfulCreatedTaskDTO createTask(TaskCreateDTO task, String userId, String proyectId){

        Role role = this.roleRepository.findRoleBetweenUserAndProject(userId, proyectId);
        TaskCreateValidator.validate(task, role);

        User userAssigned = role.user();
        Project projectBelonging = role.project();

        Task taskSaved = this.taskRepository.saveTask(
                new Task(
                        null,
                        task.description(),
                        0,
                        task.title(),
                        0,
                        userAssigned,
                        projectBelonging,
                        new Status()
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
