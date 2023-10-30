package com.dinamitaexplosivainsana.nojira.infrastructure.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.dinamitaexplosivainsana.nojira.domain.config.Constants.BEARER_AUTHENTICATION_SCHEME_NAME;

@RestController
@RequestMapping("projects")
@SecurityRequirement(name = BEARER_AUTHENTICATION_SCHEME_NAME)
@Tag(name = "Projects", description = "The projects controller contains all operations related to projects CRUD.")
public class ProjectsController {

	@GetMapping
	public String getAllProjects() {
		return "Solo deberías ver esto autenticado.";
	}
}
