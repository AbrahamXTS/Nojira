package com.dinamitaexplosivainsana.nojira.infrastructure.repositories;

import com.dinamitaexplosivainsana.nojira.application.repositories.TaskRepository;
import com.dinamitaexplosivainsana.nojira.domain.models.Task;

import java.util.ArrayList;
import java.util.Collections;

import com.dinamitaexplosivainsana.nojira.infrastructure.schemas.TaskSchema;
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
    public List<Task> getAllTasksByProjectId(String projectId) {
        List<TaskSchema> taskSchemas = taskRepository.getTaskSchemasByProject_Id(projectId);
        List<Task> tasks = new ArrayList<>();
        for(TaskSchema taskSchema : taskSchemas){
            Task task = new Task(taskSchema.getId(),taskSchema.getDescription(),Integer.parseInt(taskSchema.getEstimated().toString()),Integer.parseInt(taskSchema.getTotal().toString()),taskSchema.getProject().getId(),taskSchema.getUser().getId(),taskSchema.getTitle(),taskSchema.getStatus().getId());
            tasks.add(task);
        }
        return tasks;
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
