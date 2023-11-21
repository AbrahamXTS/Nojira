package com.dinamitaexplosivainsana.nojira.infrastructure.repositories;
import com.dinamitaexplosivainsana.nojira.application.repositories.TaskRepository;
import com.dinamitaexplosivainsana.nojira.domain.models.Task;
import com.dinamitaexplosivainsana.nojira.domain.models.User;
import com.dinamitaexplosivainsana.nojira.infrastructure.schemas.TaskSchema;
import java.util.*;
import java.util.stream.Collectors;
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
                taskSchema.getTitle(),
                taskSchema.getDescription(),
                taskSchema.getStatus().getType(),
                taskSchema.getTimeEstimatedInMinutes(),
                taskSchema.getTimeUsedInMinutes(),
                new User(
                        taskSchema.getUser().getId(),
                        taskSchema.getUser().getFullName(),
                        taskSchema.getUser().getEmail(),
                        taskSchema.getUser().getEmail()
                )
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
                        taskItem.getTitle(),
                        taskItem.getDescription(),
                        taskItem.getStatus().getType(),
                        taskItem.getTimeEstimatedInMinutes(),
                        taskItem.getTimeUsedInMinutes(),
                        new User(
                                taskItem.getUser().getId(),
                                taskItem.getUser().getFullName(),
                                taskItem.getUser().getEmail(),
                                taskItem.getUser().getEmail()
                        )
                ))
                .collect(Collectors.toList());
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
                taskSchema.getTitle(),
                taskSchema.getDescription(),
                taskSchema.getStatus().getType(),
                taskSchema.getTimeEstimatedInMinutes(),
                taskSchema.getTimeUsedInMinutes(),
                new User(
                        taskSchema.getUser().getId(),
                        taskSchema.getUser().getFullName(),
                        taskSchema.getUser().getEmail(),
                        taskSchema.getUser().getEmail()
                )
        );
    }

    @Override
    public Task updateTaskByTaskId(String taskId, Task task) {
        TaskSchema taskSchema = this.taskRepository.getReferenceById(taskId);
        taskSchema.setTimeEstimatedInMinutes(task.timeEstimatedInMinutes());
        taskSchema.setTimeUsedInMinutes(task.timeUsedInMinutes());
        TaskSchema updatedTask = this.taskRepository.save(taskSchema);
        return new Task(
                updatedTask.getId(),
                updatedTask.getTitle(),
                updatedTask.getDescription(),
                updatedTask.getStatus().getType(),
                updatedTask.getTimeEstimatedInMinutes(),
                updatedTask.getTimeUsedInMinutes(),
                new User(
                        updatedTask.getUser().getId(),
                        updatedTask.getUser().getFullName(),
                        updatedTask.getUser().getEmail(),
                        updatedTask.getUser().getEmail()
                )
        );
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
                        taskItem.getTitle(),
                        taskItem.getDescription(),
                        taskItem.getStatus().getType(),
                        taskItem.getTimeEstimatedInMinutes(),
                        taskItem.getTimeUsedInMinutes(),
                        new User(
                                taskItem.getUser().getId(),
                                taskItem.getUser().getFullName(),
                                taskItem.getUser().getEmail(),
                                taskItem.getUser().getEmail()
                        )
                ))
                .collect(Collectors.toList());
        return taskList;
    }
}


