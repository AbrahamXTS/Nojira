package com.dinamitaexplosivainsana.nojira.application.services;

import com.dinamitaexplosivainsana.nojira.application.repositories.TaskRepository;
import com.dinamitaexplosivainsana.nojira.domain.dto.SuccessfulEliminationTaskDTO;
import com.dinamitaexplosivainsana.nojira.domain.exceptions.DeleteTaskException;
import com.dinamitaexplosivainsana.nojira.domain.models.Task;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.dinamitaexplosivainsana.nojira.domain.config.Constants.DELETE_TASK_EXCEPTION_MESSAGE;

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

        boolean tasksAreNull = Objects.isNull(tasksByUserId)||Objects.isNull(tasksByProjectId)||Objects.isNull(findTask);
        if(tasksAreNull||!(tasksByUserId.contains(findTask)) ||!(tasksByProjectId.contains(findTask)) ){
            throw new DeleteTaskException(DELETE_TASK_EXCEPTION_MESSAGE);
        }

        this.taskRepository.deleteTaskByTaskId(taskId);

        return new SuccessfulEliminationTaskDTO(
            taskId
        );
    }

}
