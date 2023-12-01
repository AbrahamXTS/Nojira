package com.dinamitaexplosivainsana.nojira.infrastructure.controllers;

import com.dinamitaexplosivainsana.nojira.application.services.TaskService;
import com.dinamitaexplosivainsana.nojira.domain.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.dinamitaexplosivainsana.nojira.domain.config.Constants.BEARER_AUTHENTICATION_SCHEME_NAME;

@RestController
@SecurityRequirement(name = BEARER_AUTHENTICATION_SCHEME_NAME)
@CrossOrigin(maxAge = 3600, methods = {RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}, origins = {"*"})
@Tag(name = "Tasks", description = "The tasks controller contains all operations related to tasks CRUD.")
public class TasksController {
    private final TaskService taskService;

    public TasksController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("user/{userId}/projects/{projectId}/tasks/{taskId}")
    @Operation(summary = "Get a task by task id", description = "Endpoint to get a task by task id.")
    public ResponseEntity<WrapperResponse<TaskDTO>> getTask(
            @PathVariable String userId,
            @PathVariable String projectId,
            @PathVariable String taskId
    ) {
        return new ResponseEntity<>(
                new WrapperResponse<>(
                        true,
                        "¡Tarea obtenida correctamente!",
                        taskService.getTask(taskId, userId, projectId)
                ),
                HttpStatus.OK
        );
    }

    @GetMapping("/user/{userId}/projects/{projectId}/tasks")
    @Operation(summary = "Get all tasks by project id", description = "Endpoint to get all tasks by project id.")
    public ResponseEntity<WrapperResponse<ProjectWithTasksDTO>> getAllTasksPerProject(
            @PathVariable String userId,
            @PathVariable String projectId
    ) {
        return new ResponseEntity<>(
                new WrapperResponse<>(
                        true,
                        "Tareas del proyecto " + projectId,
                        taskService.getAllTasksByProjectId(projectId)),
                HttpStatus.OK
        );
    }

    @PostMapping("/user/{userId}/projects/{projectId}/tasks")
    @Operation(summary = "Create a new task", description = "Endpoint to create a new task related to a project.")
    public ResponseEntity<WrapperResponse<TaskDTO>> createTask(
            @PathVariable String userId,
            @PathVariable String projectId,
            @RequestBody TaskCreateDTO task
    ) {
        return new ResponseEntity<>(
                new WrapperResponse<>(
                        true,
                        "¡Tarea creada correctamente!",
                        taskService.createTask(task, userId, projectId)
                ),
                HttpStatus.CREATED
        );
    }

    @DeleteMapping("/user/{userId}/projects/{projectId}/tasks/{taskId}")
    @Operation(summary = "Delete task by taskId", description = "Endpoint to delete a task by taskId.")
    public ResponseEntity<WrapperResponse<TaskDTO>> deleteTask(
            @PathVariable String userId,
            @PathVariable String projectId,
            @PathVariable String taskId
    ) {
        return new ResponseEntity<>(
                new WrapperResponse<>(
                        true,
                        "¡Tarea eliminada correctamente!",
                        taskService.deleteTask(taskId, userId, projectId)
                ),
                HttpStatus.OK
        );
    }

    @PutMapping("/user/{userId}/projects/{projectId}/tasks/{taskId}")
    @Operation(summary = "Update all information related to a task", description = "Endpoint to update all information related to a task.")
    public ResponseEntity<WrapperResponse<TaskDTO>> updateTask(
            @PathVariable String userId,
            @PathVariable String projectId,
            @PathVariable String taskId,
            @RequestBody TaskDTO taskUpdate
    ) {
        return new ResponseEntity<>(
                new WrapperResponse<>(
                        true,
                        "¡Tarea actualizada correctamente!",
                        taskService.updateTask(taskUpdate, userId, projectId, taskId)
                ),
                HttpStatus.OK
        );
    }
}
