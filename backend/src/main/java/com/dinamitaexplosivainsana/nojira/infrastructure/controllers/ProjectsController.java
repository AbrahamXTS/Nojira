package com.dinamitaexplosivainsana.nojira.infrastructure.controllers;

import com.dinamitaexplosivainsana.nojira.application.services.ProjectService;
import com.dinamitaexplosivainsana.nojira.domain.dto.CreateProjectDTO;
import com.dinamitaexplosivainsana.nojira.domain.dto.CreatedProjectManagementDTO;
import com.dinamitaexplosivainsana.nojira.domain.dto.ProjectDTO;
import com.dinamitaexplosivainsana.nojira.domain.dto.ProjectInfoDTO;
import com.dinamitaexplosivainsana.nojira.domain.dto.WrapperResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.dinamitaexplosivainsana.nojira.domain.config.Constants.BEARER_AUTHENTICATION_SCHEME_NAME;

@RestController
@RequestMapping("user")
@SecurityRequirement(name = BEARER_AUTHENTICATION_SCHEME_NAME)
@CrossOrigin(maxAge = 3600, methods = {RequestMethod.OPTIONS, RequestMethod.GET}, origins = {"*"})
@Tag(name = "Projects", description = "The projects controller contains all operations related to projects CRUD.")
public class ProjectsController {
    private final ProjectService projectService;

    @Autowired
    public ProjectsController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/user/{id}/projects")
    public ResponseEntity<WrapperResponse<CreatedProjectManagementDTO>> createProject(
            @RequestBody CreateProjectDTO project,
            @PathVariable(value = "id") String userId
    ) {
        return new ResponseEntity<>(new WrapperResponse<>(
                true,
                "¡El proyecto "+ project.projectName() +" fue creado correctamente!",
                projectService.create(project, userId)
        ),
                HttpStatus.CREATED
        );

    }
    @GetMapping("/{userId}/projects")
    public ResponseEntity<WrapperResponse<List<ProjectDTO>>> getAllProjects(@PathVariable String userId) {
        return new ResponseEntity<>(new WrapperResponse<>(
                true,
                "Todos los proyectos",
                projectService.getAllProjectsByUserId(userId)),
                HttpStatus.OK);
    }

    @GetMapping("/{userId}/projects/{projectId}/tasks")
    public ResponseEntity<WrapperResponse<List<ProjectInfoDTO>>> getAllTasksPerProject(@PathVariable String userId, @PathVariable String projectId) {
        return new ResponseEntity<>(new WrapperResponse<>(
                true,
                "Todas las tareas",
                projectService.getAllTasksPerProject(userId, projectId)),
                HttpStatus.OK);
    }
}
