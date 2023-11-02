package com.dinamitaexplosivainsana.nojira.application.services;

import com.dinamitaexplosivainsana.nojira.application.repositories.ProjectRepository;
import com.dinamitaexplosivainsana.nojira.application.repositories.RoleRepository;
import com.dinamitaexplosivainsana.nojira.application.repositories.UserRepository;
import com.dinamitaexplosivainsana.nojira.domain.dto.CreateProjectDTO;
import com.dinamitaexplosivainsana.nojira.domain.dto.CreatedProjectManagementDTO;
import com.dinamitaexplosivainsana.nojira.domain.dto.OwnerDTO;
import com.dinamitaexplosivainsana.nojira.domain.models.Project;
import com.dinamitaexplosivainsana.nojira.domain.models.User;
import com.dinamitaexplosivainsana.nojira.domain.validators.CreateProjectValidator;

public class ProjectService {
    private final ProjectRepository projectRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public ProjectService(ProjectRepository projectRepository, RoleRepository roleRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    /**
     *
     * @param project Info provided for creating a new project
     * @param userId User creating a project
     * @return Data contract of the new project that will return to view
     */
    public CreatedProjectManagementDTO createProject(CreateProjectDTO project, String userId) {
        CreateProjectValidator.validate(project);

        Project savedProject = this.projectRepository.saveProject(
                new Project(
                        null,
                        project.projectName(),
                        project.description()
                )
        );

        User userOwner = this.userRepository.getUserByUserId(userId);

        // 0, role, is defined for project owners
        this.roleRepository.relateProjectToUser(userOwner.id(), savedProject.id(), 0);

        return new CreatedProjectManagementDTO(
                savedProject.id(),
                savedProject.name(),
                savedProject.description(),
                new OwnerDTO(
                        userOwner.id(),
                        userOwner.fullName()
                )
        );
    }
}
