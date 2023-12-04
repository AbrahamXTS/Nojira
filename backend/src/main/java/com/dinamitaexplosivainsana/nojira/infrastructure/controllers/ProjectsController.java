package com.dinamitaexplosivainsana.nojira.infrastructure.controllers;

import com.dinamitaexplosivainsana.nojira.application.services.ProjectService;
import com.dinamitaexplosivainsana.nojira.domain.dto.CreateProjectDTO;
import com.dinamitaexplosivainsana.nojira.domain.dto.ParticipantDTO;
import com.dinamitaexplosivainsana.nojira.domain.dto.ProjectDTO;
import com.dinamitaexplosivainsana.nojira.domain.dto.WrapperResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.dinamitaexplosivainsana.nojira.domain.config.Constants.BEARER_AUTHENTICATION_SCHEME_NAME;
/**
 * Controller class handling operations related to projects CRUD.
 * This controller contains endpoints for retrieving participants by project ID,
 * retrieving projects by user ID, and creating a new project.
 *
 * @RestController Indicates that this class is a Spring MVC controller.
 * @SecurityRequirement Specifies the security requirements for the Swagger documentation.
 * @CrossOrigin Enables cross-origin resource sharing (CORS) for specific methods and origins.
 * @Tag(name = "Projects", description = "The projects controller contains all operations related to projects CRUD.")
 * Swagger annotation providing metadata about the API.
 */
@RestController
@SecurityRequirement(name = BEARER_AUTHENTICATION_SCHEME_NAME)
@CrossOrigin(maxAge = 3600, methods = {RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST}, origins = {"*"})
@Tag(name = "Projects", description = "The projects controller contains all operations related to projects CRUD.")
public class ProjectsController {
    private final ProjectService projectService;
/**
     * Constructs a {@code ProjectsController} instance.
     *
     * @param projectService The service responsible for project-related operations.
     */
    public ProjectsController(ProjectService projectService) {
        this.projectService = projectService;
    }
/**
     * Retrieves all participants by project ID.
     *
     * @param projectId The ID of the project.
     * @return ResponseEntity containing a wrapped response with the list of participants and HTTP status code 200 (OK).
     */
    @GetMapping("/projects/{projectId}/users")
    @Operation(summary = "Get all participants by project id", description = "Endpoint to get all participants by project id.")
    public ResponseEntity<WrapperResponse<List<ParticipantDTO>>> getAllParticipantsByProjectId(@PathVariable String projectId) {
        return new ResponseEntity<>(
                new WrapperResponse<>(
                        true,
                        "Participantes del proyecto " + projectId,
                        projectService.getAllParticipantsByProjectId(projectId)),
                HttpStatus.OK
        );
    }
/**
     * Retrieves all projects by user ID.
     *
     * @param userId The ID of the user.
     * @return ResponseEntity containing a wrapped response with the list of projects and HTTP status code 200 (OK).
     */
    @GetMapping("/user/{userId}/projects")
    @Operation(summary = "Get all projects by user id", description = "Endpoint to get all projects by user id.")
    public ResponseEntity<WrapperResponse<List<ProjectDTO>>> getAllProjectsByUserId(@PathVariable String userId) {
        return new ResponseEntity<>(
                new WrapperResponse<>(
                        true,
                        "Proyectos del usuario " + userId,
                        projectService.getAllProjectsByUserId(userId)),
                HttpStatus.OK
        );
    }
 /**
     * Creates a new project for a user.
     *
     * @param userId  The ID of the user for whom the project is created.
     * @param project The {@code CreateProjectDTO} containing project creation information.
     * @return ResponseEntity containing a wrapped response with the created project and HTTP status code 201 (CREATED).
     */
    @PostMapping("/user/{userId}/projects")
    @Operation(summary = "Create a project for a user", description = "Endpoint to create a new project.")
    public ResponseEntity<WrapperResponse<ProjectDTO>> createProject(
            @PathVariable String userId,
            @RequestBody CreateProjectDTO project
    ) {
        return new ResponseEntity<>(
                new WrapperResponse<>(
                        true,
                        "¡El proyecto " + project.title() + " fue creado correctamente!",
                        projectService.create(project, userId)
                ),
                HttpStatus.CREATED
        );
    }
}
