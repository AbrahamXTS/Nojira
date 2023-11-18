package com.dinamitaexplosivainsana.nojira.infrastructure.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dinamitaexplosivainsana.nojira.application.repositories.RoleRepository;
import com.dinamitaexplosivainsana.nojira.application.repositories.TaskRepository;
import com.dinamitaexplosivainsana.nojira.application.services.UpdateTaskService;
import com.dinamitaexplosivainsana.nojira.domain.dto.SuccessfulUpdateTaskDTO;
import com.dinamitaexplosivainsana.nojira.domain.dto.TaskDTO;
import com.dinamitaexplosivainsana.nojira.domain.dto.WrapperResponse;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import static com.dinamitaexplosivainsana.nojira.domain.config.Constants.BEARER_AUTHENTICATION_SCHEME_NAME;

@RestController
@RequestMapping("user")
@SecurityRequirement(name = BEARER_AUTHENTICATION_SCHEME_NAME)
@CrossOrigin(maxAge = 3600, methods = {RequestMethod.OPTIONS, RequestMethod.DELETE}, origins = {"*"})
@Tag(name = "updateTask", description = "The update tasks handler contains the function of update tasks from a project")
public class UpdateTaskController {

        private final UpdateTaskService updateTaskService; 

        @Autowired
        public UpdateTaskController(TaskRepository taskRepository, RoleRepository roleRepository) {
            this.updateTaskService = new UpdateTaskService(taskRepository, roleRepository); 
        }

        @PutMapping("/{userId}/projects/{projectId}/tasks/{taskId}")
        public ResponseEntity<WrapperResponse<SuccessfulUpdateTaskDTO>> updateTask(
                @PathVariable("userId") String userId,
                @PathVariable("projectId") String projectId,
                @PathVariable("taskId") String taskId, @RequestBody TaskDTO taskUpdate){
                return new ResponseEntity<>(new WrapperResponse<>(
                        true,
                        "¡Tarea actualizada correctamente!",
                        updateTaskService.updateTask(taskUpdate, userId, projectId, taskId)
                ),
                        HttpStatus.OK
                );
        }
}
