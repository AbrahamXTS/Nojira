package com.dinamitaexplosivainsana.nojira.infrastructure.repositories;

import com.dinamitaexplosivainsana.nojira.application.repositories.TaskRepository;
import com.dinamitaexplosivainsana.nojira.domain.models.Task;
import com.dinamitaexplosivainsana.nojira.infrastructure.schemas.ProjectSchema;
import com.dinamitaexplosivainsana.nojira.infrastructure.schemas.StatusCatalogSchema;
import com.dinamitaexplosivainsana.nojira.infrastructure.schemas.TaskSchema;
import com.dinamitaexplosivainsana.nojira.infrastructure.schemas.UserSchema;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class TaskRepositoryImpl implements TaskRepository {
    private final JPATaskRepository taskRepository;
    private final JPAUserRepository userRepository;
    private final JPAProjectRepository projectRepository;
    private final JPAStatusRepository statusRepository;

    public TaskRepositoryImpl(JPATaskRepository taskRepository,
                              JPAUserRepository userRepository,
                              JPAProjectRepository projectRepository,
                              JPAStatusRepository statusRepository
    ) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
        this.statusRepository = statusRepository;
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
                taskSchema.getTimeUsedInMinutes(),
                taskSchema.getUser().getId(),
                taskSchema.getProject().getId(),
                taskSchema.getStatus().getId().toString()
        );
    }

    @Override
    public List<Task> getAllTasksByUserId(String userId) {
        List<TaskSchema> tasksByUserId = this.taskRepository.getAllTasksByUserId(userId);
        if (Objects.isNull(tasksByUserId)){
            return null;
        }
        List<Task> taskList = tasksByUserId.stream()
                .map(taskItem -> new Task(
                        taskItem.getId(),
                        taskItem.getDescription(),
                        taskItem.getTimeEstimatedInMinutes(),
                        taskItem.getTitle(),
                        taskItem.getTimeUsedInMinutes(),
                        taskItem.getUser().getId(),
                        taskItem.getProject().getId(),
                        taskItem.getStatus().getId().toString()
                ))
                .collect(Collectors.toList());
        return taskList;
    }

    @Override
    public Task saveTask(Task task) {
        UserSchema user = this.userRepository.findById(task.userId())
                .orElse(null);
        ProjectSchema project = this.projectRepository.findById(task.projectId())
                .orElse(null);
        StatusCatalogSchema status = this.statusRepository.findById(Integer.getInteger(task.statusId()))
                .orElse(null);

        TaskSchema taskSchema = this.taskRepository.save(
                TaskSchema.builder()
                        .title(task.title())
                        .description(task.description())
                        .timeUsedInMinutes(task.timeUsedInMinutes())
                        .timeEstimatedInMinutes(task.timeEstimatedInMinutes())
                        .project(project)
                        .user(user)
                        .status(status)
                        .build()
        );

        return new Task(
                taskSchema.getId(),
                taskSchema.getDescription(),
                taskSchema.getTimeUsedInMinutes(),
                taskSchema.getTitle(),
                taskSchema.getTimeUsedInMinutes(),
                taskSchema.getUser().getId(),
                taskSchema.getProject().getId(),
                taskSchema.getStatus().getId().toString()
        );
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
                taskSchema.getTimeUsedInMinutes(),
                taskSchema.getTitle(),
                taskSchema.getTimeUsedInMinutes(),
                taskSchema.getUser().getId(),
                taskSchema.getProject().getId(),
                taskSchema.getStatus().getType()
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
                        taskItem.getTimeUsedInMinutes(),
                        taskItem.getUser().getId(),
                        taskItem.getProject().getId(),
                        taskItem.getStatus().getId().toString()
                ))
                .collect(Collectors.toList());
        return taskList;
    }
}
