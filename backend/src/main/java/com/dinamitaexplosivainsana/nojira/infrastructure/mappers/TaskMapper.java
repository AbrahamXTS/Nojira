package com.dinamitaexplosivainsana.nojira.infrastructure.mappers;

import com.dinamitaexplosivainsana.nojira.domain.models.Task;
import com.dinamitaexplosivainsana.nojira.infrastructure.schemas.TaskSchema;

public class TaskMapper {
    private TaskMapper() {
    }

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
