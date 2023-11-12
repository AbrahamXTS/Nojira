package com.dinamitaexplosivainsana.nojira.infrastructure.controllers;

import com.dinamitaexplosivainsana.nojira.application.repositories.StatusRepository;
import com.dinamitaexplosivainsana.nojira.application.repositories.TaskDBRepository;
import com.dinamitaexplosivainsana.nojira.application.repositories.TaskRepository;
import com.dinamitaexplosivainsana.nojira.application.repositories.UserRepository;
import com.dinamitaexplosivainsana.nojira.application.services.TaskService;
import com.dinamitaexplosivainsana.nojira.domain.dto.SuccessfulCreatedTaskDTO;
import com.dinamitaexplosivainsana.nojira.domain.dto.TaskCreateDTO;
import com.dinamitaexplosivainsana.nojira.domain.dto.WrapperResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("tasks")
@CrossOrigin(maxAge = 3600, methods = {RequestMethod.OPTIONS, RequestMethod.POST}, origins = {"*"})
@Tag(name = "Tasks", description = "The tasks controller contains all operations related to tasks CRUD.")
public class TasksController {
    private final TaskService taskService;

    @Autowired
    public TasksController(TaskDBRepository taskDBRepository,
                           StatusRepository statusRepository,
                           UserRepository userRepository)
    {
        this.taskService = new TaskService(taskDBRepository, statusRepository, userRepository);
    }

    @PostMapping("/user/{userId}/proyects/{proyectId}/tasks")
    @ResponseBody
    public ResponseEntity<WrapperResponse<SuccessfulCreatedTaskDTO>> createTask(
            @RequestBody TaskCreateDTO task,
            @PathVariable String userId,
            @PathVariable String proyectId
    ){
        return new ResponseEntity<>(new WrapperResponse<>(
                true,
                    "¡Tarea creada correctamente!",
                taskService.createTask(task, userId, proyectId)
        ),
                HttpStatus.CREATED
        );
    }


}
