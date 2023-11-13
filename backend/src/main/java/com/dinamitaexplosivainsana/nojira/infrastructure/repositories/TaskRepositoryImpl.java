package com.dinamitaexplosivainsana.nojira.infrastructure.repositories;

import com.dinamitaexplosivainsana.nojira.application.repositories.TaskRepository;
import com.dinamitaexplosivainsana.nojira.domain.models.Project;
import com.dinamitaexplosivainsana.nojira.domain.models.Status;
import com.dinamitaexplosivainsana.nojira.domain.models.Task;
import com.dinamitaexplosivainsana.nojira.domain.models.User;
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
                taskSchema.getTimeUsedInMinutes(),
                new User(taskSchema.getUser().getId(),
                        taskSchema.getUser().getFullName(),
                        taskSchema.getUser().getEmail(),
                        taskSchema.getUser().getPassword()
                ),
                new Project(
                        taskSchema.getProject().getId(),
                        taskSchema.getProject().getName(),
                        taskSchema.getProject().getDescription()
                ),
                new Status(
                        taskSchema.getStatus().getId(),
                        taskSchema.getStatus().getType()
                )
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
                        new User(taskItem.getUser().getId(),
                                taskItem.getUser().getFullName(),
                                taskItem.getUser().getEmail(),
                                taskItem.getUser().getPassword()
                        ),
                        new Project(
                                taskItem.getProject().getId(),
                                taskItem.getProject().getName(),
                                taskItem.getProject().getDescription()
                        ),
                        new Status(
                                taskItem.getStatus().getId(),
                                taskItem.getStatus().getType()
                        )
                ))
                .collect(Collectors.toList());
        return taskList;
    }

    @Override
    public Task saveTask(Task task) {
        UserSchema user = UserSchema.builder()
                .id(task.userAsigned().id())
                .fullName(task.userAsigned().fullName())
                .email(task.userAsigned().email())
                .password(task.userAsigned().password())
                .build();
        ProjectSchema project =  ProjectSchema.builder()
                .id(task.projectBelonging().id())
                .name(task.projectBelonging().name())
                .description(task.projectBelonging().description())
                .build();
        StatusCatalogSchema status = StatusCatalogSchema.builder()
                .id(task.status().id())
                .type(task.status().type())
                .build();

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
                new User(taskSchema.getUser().getId(),
                        taskSchema.getUser().getFullName(),
                        taskSchema.getUser().getEmail(),
                        taskSchema.getUser().getPassword()
                ),
                new Project(
                        taskSchema.getProject().getId(),
                        taskSchema.getProject().getName(),
                        taskSchema.getProject().getDescription()
                ),
                new Status(
                        taskSchema.getStatus().getId(),
                        taskSchema.getStatus().getType()
                )
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
                new User(taskSchema.getUser().getId(),
                        taskSchema.getUser().getFullName(),
                        taskSchema.getUser().getEmail(),
                        taskSchema.getUser().getPassword()
                ),
                new Project(
                        taskSchema.getProject().getId(),
                        taskSchema.getProject().getName(),
                        taskSchema.getProject().getDescription()
                ),
                new Status(
                        taskSchema.getStatus().getId(),
                        taskSchema.getStatus().getType()
                )
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
                        new User(taskItem.getUser().getId(),
                                taskItem.getUser().getFullName(),
                                taskItem.getUser().getEmail(),
                                taskItem.getUser().getPassword()
                        ),
                        new Project(
                                taskItem.getProject().getId(),
                                taskItem.getProject().getName(),
                                taskItem.getProject().getDescription()
                        ),
                        new Status(
                                taskItem.getStatus().getId(),
                                taskItem.getStatus().getType()
                        )
                ))
                .collect(Collectors.toList());
        return taskList;
    }
}
