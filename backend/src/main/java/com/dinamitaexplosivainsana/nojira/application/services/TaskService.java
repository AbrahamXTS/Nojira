package com.dinamitaexplosivainsana.nojira.application.services;

import com.dinamitaexplosivainsana.nojira.application.repositories.ProjectRepository;
import com.dinamitaexplosivainsana.nojira.application.repositories.StatusRepository;
import com.dinamitaexplosivainsana.nojira.application.repositories.TaskRepository;
import com.dinamitaexplosivainsana.nojira.application.repositories.UserRepository;
import com.dinamitaexplosivainsana.nojira.domain.dto.OwnerDTO;
import com.dinamitaexplosivainsana.nojira.domain.dto.TaskDTO;
import com.dinamitaexplosivainsana.nojira.domain.dto.ChangeStatusOfTaskRequestDTO;
import com.dinamitaexplosivainsana.nojira.domain.dto.TimesDTO;
import com.dinamitaexplosivainsana.nojira.domain.exceptions.ResourceNotFoundException;
import com.dinamitaexplosivainsana.nojira.domain.models.Status;
import com.dinamitaexplosivainsana.nojira.domain.models.Task;
import com.dinamitaexplosivainsana.nojira.domain.models.User;

import java.util.List;
import java.util.Objects;

public class TaskService {
    private final ProjectRepository projectRepository;
    private final StatusRepository statusRepository;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(
            ProjectRepository projectRepository,
            StatusRepository statusRepository,
            TaskRepository taskRepository,
            UserRepository userRepository
    ) {
        this.projectRepository = projectRepository;
        this.statusRepository = statusRepository;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public TaskDTO assignStatus(
            String userId,
            String projectId,
            String taskId,
            ChangeStatusOfTaskRequestDTO statusDTO
    ) {
        Task task = taskRepository.getTaskByTaskId(taskId);
        List<Task> tasksByUserId = taskRepository.getAllTasksByUserId(userId);
        List<Task> tasksByProjectId = projectRepository.getAllTasksByProjectId(projectId);

        if (Objects.isNull(task) || !(tasksByUserId.contains(task)) || !(tasksByProjectId.contains(task))) {
            throw new ResourceNotFoundException("Ops! La tarea que tratas de modificar no existe");
        }

        Status newStatus = statusRepository.getStatusByStatusId(statusDTO.statusId());

        if (Objects.isNull(newStatus)) {
            throw new ResourceNotFoundException("Ops! El estatus indicado es invalido");
        }

        User owner = userRepository.getUserByUserId(userId);

        return new TaskDTO(
                task.id(),
                task.title(),
                task.description(),
                newStatus.type(),
                new TimesDTO(task.estimatedInMinutes(), task.usedInMinutes()),
                new OwnerDTO(owner.id(), owner.fullName())
        );
    }
}
