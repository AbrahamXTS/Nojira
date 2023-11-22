package com.dinamitaexplosivainsana.nojira.application.services;

import com.dinamitaexplosivainsana.nojira.application.repositories.RoleRepository;
import com.dinamitaexplosivainsana.nojira.application.repositories.TaskRepository;
import com.dinamitaexplosivainsana.nojira.domain.dto.SuccessfulUpdateTaskDTO;
import com.dinamitaexplosivainsana.nojira.domain.dto.TaskAsignedDTO;
import com.dinamitaexplosivainsana.nojira.domain.dto.TaskTimesDTO;
import com.dinamitaexplosivainsana.nojira.domain.dto.TaskDTO;
import com.dinamitaexplosivainsana.nojira.domain.models.Role;
import com.dinamitaexplosivainsana.nojira.domain.models.Task;
import com.dinamitaexplosivainsana.nojira.domain.validators.TaskAccesValidator;
import com.dinamitaexplosivainsana.nojira.domain.validators.TaskDataValidator;
public class UpdateTaskService {
    private final TaskRepository taskRepository;
    private final RoleRepository roleRepository; 

    public UpdateTaskService(TaskRepository taskRepository, RoleRepository roleRepository) {
        this.taskRepository = taskRepository;
        this.roleRepository = roleRepository; 
    }

    public SuccessfulUpdateTaskDTO updateTask(TaskDTO task, String userId, String projectId, String taskId) {

        TaskDataValidator.validateTaskDataIsNotEmpty(task);
        
        Role roleBetweenUserAndProject = this.roleRepository.findRoleBetweenUserAndProject(userId, projectId);
        Task findedTask = this.taskRepository.getTaskByTaskId(taskId);
        TaskAccesValidator.validateAcces(roleBetweenUserAndProject, findedTask);

        Task taskUpdated = taskRepository.updateTaskByTaskId(taskId, 
            new Task(
                findedTask.id(), 
                task.description(), 
                findedTask.timeEstimatedInMinutes(), 
                task.title(), 
                findedTask.timeUsedInMinutes(), 
                findedTask.userAsigned(), 
                findedTask.projectBelonging(), 
                findedTask.status()
            )
        );

        return new SuccessfulUpdateTaskDTO(
                taskId,
                taskUpdated.title(),
                taskUpdated.description(),
                taskUpdated.status().type(),
                new TaskTimesDTO(
                    taskUpdated.timeEstimatedInMinutes(), 
                    taskUpdated.timeUsedInMinutes()
                ),
                new TaskAsignedDTO(
                    taskUpdated.userAsigned().id(), 
                    taskUpdated.userAsigned().fullName()
                )
        );
    }

}
