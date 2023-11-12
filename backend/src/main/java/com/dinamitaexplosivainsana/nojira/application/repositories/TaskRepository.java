package com.dinamitaexplosivainsana.nojira.application.repositories;

import com.dinamitaexplosivainsana.nojira.domain.models.Task;

import java.util.List;

public interface TaskRepository {
    Task getTaskByTaskId(String taskId);

    List<Task> getAllTasksByUserId(String userId);

    Task saveTask(Task task);

    Task deleteTaskByTaskId(String taskId);

    Task updateTaskByTaskId(String taskId, Task task);

    List<Task> getAllTaskByProjectId(String projectId);
}
