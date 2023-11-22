package com.dinamitaexplosivainsana.nojira.infrastructure.repositories;

import com.dinamitaexplosivainsana.nojira.application.repositories.TaskRepository;
import com.dinamitaexplosivainsana.nojira.domain.models.Task;
import com.dinamitaexplosivainsana.nojira.infrastructure.schemas.TaskSchema;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Component
public class TaskRepositoryImpl implements TaskRepository {
    private final JPATaskRepository taskRepository;

    public TaskRepositoryImpl(JPATaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task getTaskByTaskId(String taskId) {
        TaskSchema taskSchema = this.taskRepository.findById(taskId).orElse(null);
        if (Objects.isNull(taskSchema)) {
            return null;
        }
        return new Task(
                taskSchema.getId(),
                taskSchema.getDescription(),
                taskSchema.getTimeEstimatedInMinutes(),
                taskSchema.getTitle(),
                taskSchema.getTimeUsedInMinutes()
        );
    }

    @Override
    public List<Task> getAllTasksByUserId(String userId) {
        List<TaskSchema> tasksByUserId = this.taskRepository.getAllTasksByUserId(userId);
        if (Objects.isNull(tasksByUserId)) {
            return null;
        }
        List<Task> taskList = tasksByUserId.stream()
                .map(taskItem -> new Task(
                        taskItem.getId(),
                        taskItem.getDescription(),
                        taskItem.getTimeEstimatedInMinutes(),
                        taskItem.getTitle(),
                        taskItem.getTimeUsedInMinutes()
                ))
                .collect(Collectors.toList());
        return taskList;
    }

    @Override
    public List<Task> getAllTasksByProjectId(String projectId) {
        List<TaskSchema> taskSchemas = taskRepository.getTaskSchemasByProject_Id(projectId);
        if (Objects.isNull(taskSchemas)) {
            return null;
        }
        return taskSchemas.stream()
                .map(this::mapTaskSchemaToTask)
                .collect(Collectors.toList());
    }

    private Task mapTaskSchemaToTask(TaskSchema taskSchema) {
        return new Task(
                taskSchema.getId(),
                taskSchema.getDescription(),
                taskSchema.getTimeEstimatedInMinutes(),
                taskSchema.getTimeUsedInMinutes(),
                taskSchema.getProject().getId(),
                taskSchema.getUser().getId(),
                taskSchema.getTitle(),
                taskSchema.getStatus().getId()
        );
    }

    @Override
    public Task saveTask(Task task) {
        return null;
    }

    @Override
    public Task deleteTaskByTaskId(String taskId) {
        TaskSchema taskSchema = this.taskRepository.findById(taskId).orElse(null);
        if (Objects.isNull(taskSchema)) {
            return null;
        }
        this.taskRepository.delete(taskSchema);
        return new Task(
                taskSchema.getId(),
                taskSchema.getDescription(),
                taskSchema.getTimeEstimatedInMinutes(),
                taskSchema.getTitle(),
                taskSchema.getTimeUsedInMinutes()
        );
    }

    @Override
    public Task updateTaskByTaskId(String taskId, Task task) {
        return null;
    }

    @Override
    public List<Task> getAllTaskByProjectId(String projectId) {
        List<TaskSchema> tasksByProjectId = this.taskRepository.getAllTasksByProjectId(projectId);

        if (Objects.isNull(tasksByProjectId)) {
            return null;
        }
        List<Task> taskList = tasksByProjectId.stream()
                .map(taskItem -> new Task(
                        taskItem.getId(),
                        taskItem.getDescription(),
                        taskItem.getTimeEstimatedInMinutes(),
                        taskItem.getTitle(),
                        taskItem.getTimeUsedInMinutes()
                ))
                .collect(Collectors.toList());
        return taskList;
    }
}
