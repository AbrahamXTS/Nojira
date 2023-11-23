package com.dinamitaexplosivainsana.nojira.infrastructure.controllers;

import com.dinamitaexplosivainsana.nojira.application.services.TaskService;
import com.dinamitaexplosivainsana.nojira.domain.dto.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(maxAge = 3600, methods = {RequestMethod.OPTIONS, RequestMethod.PUT}, origins = {"*"})
@Tag(name = "Tasks", description = "The task controller allows you to perform various operations with the tasks of a project.")
public class TasksController {
    private final TaskService taskService;

    @Autowired
    public TasksController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PutMapping("user/{userId}/projects/{projectId}/tasks/{taskId}/status")
    public ResponseEntity<WrapperResponse<TaskDTO>> assignStatusToTask(
            @PathVariable String userId,
            @PathVariable String projectId,
            @PathVariable String taskId,
            @RequestBody ChangeStatusOfTaskRequestDTO statusDTO
    ) {
        return new ResponseEntity<>(new WrapperResponse<>(
                true,
                "Tarea cambiada al estatus" + statusDTO.statusId(),
                taskService.assignStatus(userId, projectId, taskId, statusDTO)
        ),
                HttpStatus.OK
        );
    }
}
