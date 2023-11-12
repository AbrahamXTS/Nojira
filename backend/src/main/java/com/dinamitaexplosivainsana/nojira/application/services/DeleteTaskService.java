package com.dinamitaexplosivainsana.nojira.application.services;

import com.dinamitaexplosivainsana.nojira.application.repositories.TaskRepository;
import com.dinamitaexplosivainsana.nojira.domain.dto.SuccessfulEliminationTaskDTO;
import com.dinamitaexplosivainsana.nojira.domain.exceptions.TaskNotFoundException;
import com.dinamitaexplosivainsana.nojira.domain.models.Task;

import static com.dinamitaexplosivainsana.nojira.domain.config.Constants.TASK_NOT_FOUND_EXCEPTION_MESSAGE;

import java.util.List;
import java.util.Objects;

public class DeleteTaskService {
    private final TaskRepository taskRepository;

    public DeleteTaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    public SuccessfulEliminationTaskDTO delete(String taskId, String userId, String projectId){
        Task findTask = this.taskRepository.getTaskByTaskId(taskId);
        List<Task> tasksByUserId= this.taskRepository.getAllTasksByUserId(userId);
        List<Task> tasksByProjectId= this.taskRepository.getAllTaskByProjectId(projectId);

        boolean tasksAreNull = Objects.isNull(tasksByUserId)
                ||Objects.isNull(tasksByProjectId)
                ||Objects.isNull(findTask);

        if(tasksAreNull||!(tasksByUserId.contains(findTask)) ||!(tasksByProjectId.contains(findTask)) ){
            throw new TaskNotFoundException(TASK_NOT_FOUND_EXCEPTION_MESSAGE);
        }

        this.taskRepository.deleteTaskByTaskId(taskId);

        return new SuccessfulEliminationTaskDTO(
            taskId
        );
    }

}
