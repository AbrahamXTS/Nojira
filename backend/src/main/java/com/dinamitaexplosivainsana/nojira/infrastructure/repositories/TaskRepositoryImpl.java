package com.dinamitaexplosivainsana.nojira.infrastructure.repositories;

import com.dinamitaexplosivainsana.nojira.application.repositories.TaskRepository;
import com.dinamitaexplosivainsana.nojira.domain.models.Task;
import com.dinamitaexplosivainsana.nojira.infrastructure.mappers.ProjectMapper;
import com.dinamitaexplosivainsana.nojira.infrastructure.mappers.StatusCatalogMapper;
import com.dinamitaexplosivainsana.nojira.infrastructure.mappers.TaskMapper;
import com.dinamitaexplosivainsana.nojira.infrastructure.mappers.UserMapper;
import com.dinamitaexplosivainsana.nojira.infrastructure.schemas.TaskSchema;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Repository
public class TaskRepositoryImpl implements TaskRepository {
    private final JPATaskRepository taskRepository;

    public TaskRepositoryImpl(JPATaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task getTaskByTaskId(String taskId) {
        TaskSchema taskSchema = this.taskRepository.findById(taskId)
                .orElse(null);

        if (Objects.isNull(taskSchema)) {
            return null;
        }

        return TaskMapper.mapToModel(taskSchema);
    }

    @Override
    public List<Task> getAllTasksByUserId(String userId) {
        List<TaskSchema> tasksByUserId = this.taskRepository.getAllTasksByUserId(userId);

        if (Objects.isNull(tasksByUserId)) {
            return Collections.emptyList();
        }

        return tasksByUserId.stream()
                .map(TaskMapper::mapToModel)
                .toList();
    }

    @Override
    public List<Task> getAllTasksByProjectId(String projectId) {
        List<TaskSchema> taskSchemas = taskRepository.getTaskSchemasByProjectId(projectId);

        if (Objects.isNull(taskSchemas)) {
            return Collections.emptyList();
        }

        return taskSchemas.stream()
                .map(TaskMapper::mapToModel)
                .toList();
    }

    @Override
    public Task saveTask(Task task) {
        TaskSchema taskSchema = this.taskRepository.save(TaskMapper.mapToSchema(task));

        return TaskMapper.mapToModel(taskSchema);
    }

    @Override
    public Task deleteTaskByTaskId(String taskId) {
        TaskSchema taskSchema = this.taskRepository.findById(taskId)
                .orElse(null);

        if (Objects.isNull(taskSchema)) {
            return null;
        }

        this.taskRepository.deleteById(taskId);

        return TaskMapper.mapToModel(taskSchema);
    }

    @Override
    public Task updateTaskByTaskId(String taskId, Task task) {
        TaskSchema taskSchema = this.taskRepository.getReferenceById(taskId);

        taskSchema.setDescription(task.description());
        taskSchema.setTimeEstimatedInMinutes(task.timeEstimatedInMinutes());
        taskSchema.setTitle(task.title());
        taskSchema.setTimeUsedInMinutes(task.timeUsedInMinutes());
        taskSchema.setProject(ProjectMapper.mapToSchema(task.project()));
        taskSchema.setUser(UserMapper.mapToSchema(task.user()));
        taskSchema.setStatus(StatusCatalogMapper.mapToSchema(task.statusCatalog()));

        TaskSchema updatedTask = this.taskRepository.save(taskSchema);

        return TaskMapper.mapToModel(updatedTask);
    }
}
