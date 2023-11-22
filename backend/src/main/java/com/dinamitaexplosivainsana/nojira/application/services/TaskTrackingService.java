package com.dinamitaexplosivainsana.nojira.application.services;
import com.dinamitaexplosivainsana.nojira.application.repositories.TaskRepository;
import com.dinamitaexplosivainsana.nojira.domain.dto.*;
import com.dinamitaexplosivainsana.nojira.domain.models.*;
import com.dinamitaexplosivainsana.nojira.domain.validators.TaskValidator;
import java.util.List;
public class TaskTrackingService {
    private final TaskRepository taskRepository;
    public TaskTrackingService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    public SuccessfulTaskTrackingDTO trackTask(TaskTrackingDTO taskTrackingDTO, String userId, String projectId, String taskId) {
        Task findTask = this.taskRepository.getTaskByTaskId(taskId);

        TaskValidator.validateTaskId(taskId, taskTrackingDTO);

        List<Task> tasksByUserId= this.taskRepository.getAllTasksByUserId(userId);
        List<Task> tasksByProjectId= this.taskRepository.getAllTaskByProjectId(projectId);

        TaskValidator.validateTaskLists(findTask, tasksByUserId, tasksByProjectId);

        Task updatedTask=(new Task(
                taskTrackingDTO.taskId(),
                findTask.description(),
                taskTrackingDTO.estimated(),
                findTask.title(),
                taskTrackingDTO.used(),
                findTask.userAsigned(),
                findTask.projectBelonging(),
                findTask.status()
        )
        );
        taskRepository.updateTaskByTaskId(taskId, updatedTask);

        return new SuccessfulTaskTrackingDTO(
                updatedTask.id(),
                updatedTask.title(),
                updatedTask.description(),
                updatedTask.status().type(),
                new TaskTimesDTO(
                        updatedTask.timeEstimatedInMinutes(),
                        updatedTask.timeUsedInMinutes()),
                new TaskAssignedToDTO(
                        updatedTask.userAsigned().id(),
                        updatedTask.userAsigned().fullName()
                ));
    }
}
