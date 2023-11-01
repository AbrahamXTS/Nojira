package com.dinamitaexplosivainsana.nojira.application.repositories;

import com.dinamitaexplosivainsana.nojira.domain.models.Task;

import java.util.List;

public interface TaskRepository {
    Task getTaskByTaskId(String taskId);

    List<Task> getAllTasksByUserId(String userId);

    Task saveTask(Task project);

    Task deleteTaskByTaskId(String projectId);

    Task updateTaskByTaskId(String taskId, Task task);
}
