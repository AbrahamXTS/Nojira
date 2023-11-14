package com.dinamitaexplosivainsana.nojira.infrastructure.controllers;

import com.dinamitaexplosivainsana.nojira.application.repositories.ProjectRepository;
import com.dinamitaexplosivainsana.nojira.application.repositories.RoleRepository;
import com.dinamitaexplosivainsana.nojira.application.repositories.UserRepository;
import com.dinamitaexplosivainsana.nojira.application.services.ProjectService;
import com.dinamitaexplosivainsana.nojira.domain.dto.CreateProjectDTO;
import com.dinamitaexplosivainsana.nojira.domain.dto.CreatedProjectManagementDTO;
import com.dinamitaexplosivainsana.nojira.domain.dto.WrapperResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.dinamitaexplosivainsana.nojira.domain.config.Constants.BEARER_AUTHENTICATION_SCHEME_NAME;

@RestController
@RequestMapping("projects")
@SecurityRequirement(name = BEARER_AUTHENTICATION_SCHEME_NAME)
@CrossOrigin(maxAge = 3600, methods = {RequestMethod.OPTIONS, RequestMethod.GET}, origins = {"*"})
@Tag(name = "Projects", description = "The projects controller contains all operations related to projects CRUD.")
public class ProjectsController {
    private final ProjectService projectService;

    @Autowired
    public ProjectsController(ProjectRepository projectRepository,
                              RoleRepository roleRepository,
                              UserRepository userRepository
    ) {
        this.projectService = new ProjectService(projectRepository, roleRepository, userRepository);
    }

    @GetMapping
    public String getAllProjects() {
        return "Solo deberías ver esto autenticado.";
    }

    @PostMapping("/user/{id}/projects")
    @ResponseBody
    public ResponseEntity<WrapperResponse<CreatedProjectManagementDTO>> createProject(
            @RequestBody CreateProjectDTO project,
            @PathVariable(value = "id") String userId
    ) {
        return new ResponseEntity<>(new WrapperResponse<>(
                true,
                "¡Proyecto creado correctamente!",
                projectService.createProject(project, userId)
        ),
                HttpStatus.CREATED
        );
    }
}
