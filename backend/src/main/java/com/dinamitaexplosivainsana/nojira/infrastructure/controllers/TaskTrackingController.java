package com.dinamitaexplosivainsana.nojira.infrastructure.controllers;
import com.dinamitaexplosivainsana.nojira.application.services.TaskTrackingService;
import com.dinamitaexplosivainsana.nojira.domain.dto.SuccessfulTaskTrackingDTO;
import com.dinamitaexplosivainsana.nojira.domain.dto.TaskTrackingDTO;
import com.dinamitaexplosivainsana.nojira.domain.dto.WrapperResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import static com.dinamitaexplosivainsana.nojira.domain.config.Constants.BEARER_AUTHENTICATION_SCHEME_NAME;
@RequestMapping("user")
@RestController
@SecurityRequirement(name = BEARER_AUTHENTICATION_SCHEME_NAME)
@CrossOrigin(maxAge = 3600, methods = { RequestMethod.OPTIONS, RequestMethod.PUT }, origins = { "*" })
@Tag(name = "Tasks", description = "the times assigned to the tasks were modified.")
public class TaskTrackingController {private final TaskTrackingService taskTrackingService;
    @Autowired    public TaskTrackingController(TaskTrackingService taskTrackingService) { this.taskTrackingService = taskTrackingService;}
    @ResponseBody
    @PutMapping("/{userId}/projects/{projectId}/tasks/{taskId}/time")
    public ResponseEntity<WrapperResponse<SuccessfulTaskTrackingDTO>> trackTask(
            @RequestBody TaskTrackingDTO taskTrackingDTO,
            @PathVariable String userId,
            @PathVariable String projectId,
            @PathVariable String taskId){
        return new ResponseEntity<>(new WrapperResponse<>(
                true,
                "¡Tiempos asignados correctamente!",
                taskTrackingService.trackTask(taskTrackingDTO, userId, projectId, taskId)),
                HttpStatus.OK);}}