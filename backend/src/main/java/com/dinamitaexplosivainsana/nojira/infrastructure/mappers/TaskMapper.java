package com.dinamitaexplosivainsana.nojira.infrastructure.mappers;

import com.dinamitaexplosivainsana.nojira.domain.models.Task;
import com.dinamitaexplosivainsana.nojira.infrastructure.schemas.TaskSchema;
/**
 * Mapper class responsible for converting between {@link Task} domain models
 * and {@link TaskSchema} database schemas.
 */
public class TaskMapper {
     /**
     * Private constructor to prevent instantiation of the mapper class.
     * All methods in this class are static, and it should not be instantiated.
     */
    private TaskMapper() {
    }
/**
     * Maps a {@link TaskSchema} object to a {@link Task} domain model.
     *
     * @param taskSchema The database schema representing a task.
     * @return A corresponding domain model representing the task.
     */
    public static Task mapToModel(TaskSchema taskSchema) {
        return new Task(
                taskSchema.getId(),
                taskSchema.getDescription(),
                taskSchema.getTimeEstimatedInMinutes(),
                taskSchema.getTitle(),
                taskSchema.getTimeUsedInMinutes(),
                ProjectMapper.mapToModel(taskSchema.getProject()),
                UserMapper.mapToModel(taskSchema.getUser()),
                StatusCatalogMapper.mapToModel(taskSchema.getStatus())
        );
    }
 /**
     * Maps a {@link Task} domain model to a {@link TaskSchema} database schema.
     *
     * @param task The domain model representing a task.
     * @return A corresponding database schema representing the task.
     */
    public static TaskSchema mapToSchema(Task task) {
        return new TaskSchema(
                task.id(),
                task.description(),
                task.timeEstimatedInMinutes(),
                task.title(),
                task.timeUsedInMinutes(),
                ProjectMapper.mapToSchema(task.project()),
                UserMapper.mapToSchema(task.user()),
                StatusCatalogMapper.mapToSchema(task.statusCatalog())
        );
    }
}
