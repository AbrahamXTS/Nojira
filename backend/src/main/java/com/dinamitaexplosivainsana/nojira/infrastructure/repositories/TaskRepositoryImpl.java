package com.dinamitaexplosivainsana.nojira.infrastructure.repositories;

import com.dinamitaexplosivainsana.nojira.application.repositories.TaskRepository;
import com.dinamitaexplosivainsana.nojira.domain.models.Task;
import com.dinamitaexplosivainsana.nojira.infrastructure.schemas.TaskSchema;

import java.util.*;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

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

        List<TaskSchema> taskSchema = this.taskRepository.getAllTaskByUserId(userId);
        if (Objects.isNull(taskSchema)){
            return null;
        }
        List<Task> taskList = new ArrayList<>();

        for (TaskSchema taskChange : taskSchema) {
            Task task = new Task(
                    taskChange.getId(),
                    taskChange.getDescription(),
                    taskChange.getTimeEstimatedInMinutes(),
                    taskChange.getTitle(),
                    taskChange.getTimeUsedInMinutes()
            );
            taskList.add(task);
        }
        return taskList;
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
        List<TaskSchema> taskSchema = this.taskRepository.getAllTaskByProjectId(projectId);
        if (Objects.isNull(taskSchema)) {
            return null;
        }
        List<Task> taskList = new ArrayList<>();

        for (TaskSchema taskChange : taskSchema) {
            Task task = new Task(
                    taskChange.getId(),
                    taskChange.getDescription(),
                    taskChange.getTimeEstimatedInMinutes(),
                    taskChange.getTitle(),
                    taskChange.getTimeUsedInMinutes()
            );
            taskList.add(task);
        }
        return taskList;
    }
}
