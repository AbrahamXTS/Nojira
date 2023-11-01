package com.dinamitaexplosivainsana.nojira.infrastructure.repositories;

import com.dinamitaexplosivainsana.nojira.application.repositories.TaskRepository;
import com.dinamitaexplosivainsana.nojira.domain.models.Task;
import java.util.Collections;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskRepositoryImpl implements TaskRepository {
    private final JPATaskRepository taskRepository;

    public TaskRepositoryImpl(JPATaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task getTaskByTaskId(String taskId) {
        return null;
    }

    @Override
    public List<Task> getAllTasksByUserId(String userId) {
        return Collections.emptyList();
    }

    @Override
    public Task saveTask(Task task) {
        return null;
    }

    @Override
    public Task deleteTaskByTaskId(String taskId) {
        return null;
    }

    @Override
    public Task updateTaskByTaskId(String taskId, Task task) {
        return null;
    }
}
