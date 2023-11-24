package com.dinamitaexplosivainsana.nojira.infrastructure.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import com.dinamitaexplosivainsana.nojira.application.repositories.TaskRepository;
import com.dinamitaexplosivainsana.nojira.application.services.DeleteTaskService;
import com.dinamitaexplosivainsana.nojira.domain.dto.SuccessfulEliminationTaskDTO;
import com.dinamitaexplosivainsana.nojira.domain.dto.WrapperResponse;

import io.swagger.v3.oas.annotations.tags.Tag;

import static com.dinamitaexplosivainsana.nojira.domain.config.Constants.BEARER_AUTHENTICATION_SCHEME_NAME;

@RestController
@RequestMapping("user")
@SecurityRequirement(name = BEARER_AUTHENTICATION_SCHEME_NAME)
@CrossOrigin(maxAge = 3600, methods = {RequestMethod.OPTIONS, RequestMethod.DELETE}, origins = {"*"})
@Tag(name = "deleteTask", description = "The delete tasks handler contains the function of removing tasks from a project")

public class DeleteTaskController {
        private final DeleteTaskService deleteTaskService;

        @Autowired
        public DeleteTaskController(TaskRepository taskRepository) {
            this.deleteTaskService = new DeleteTaskService(taskRepository);
        }

        @DeleteMapping("/{userId}/projects/{projectId}/tasks/{taskId}")
        public ResponseEntity<WrapperResponse<SuccessfulEliminationTaskDTO>> deleteTask(
                @PathVariable("userId") String userId,
                @PathVariable("projectId") String projectId,
                @PathVariable("taskId") String taskId){
            return new ResponseEntity<>(new WrapperResponse<>(
                    true,
                    "¡Tarea eliminada correctamente!",
                    deleteTaskService.delete(taskId, userId, projectId)
            ),
                    HttpStatus.OK
            );
        }


}
