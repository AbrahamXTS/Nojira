package com.dinamitaexplosivainsana.nojira.application.services;

import com.dinamitaexplosivainsana.nojira.application.repositories.*;
import com.dinamitaexplosivainsana.nojira.domain.dto.*;
import com.dinamitaexplosivainsana.nojira.domain.exceptions.ResourceNotFoundException;
import com.dinamitaexplosivainsana.nojira.domain.models.*;
import com.dinamitaexplosivainsana.nojira.domain.validators.CreateProjectValidator;

import java.util.List;
import java.util.Objects;

import static com.dinamitaexplosivainsana.nojira.domain.config.Constants.*;

public class ProjectService {
    private final ProjectRepository projectRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public ProjectService(
            ProjectRepository projectRepository,
            RoleRepository roleRepository,
            UserRepository userRepository
    ) {
        this.projectRepository = projectRepository;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    public List<ParticipantDTO> getAllParticipantsByProjectId(String projectId) {
        Project project = projectRepository.getProjectByProjectId(projectId);

        if (Objects.isNull(project)) {
            throw new ResourceNotFoundException(PROJECT_NOT_FOUND_EXCEPTION_MESSAGE);
        }

        List<Role> rolesByProjectId = this.roleRepository.getAllRolesByProjectId(projectId);

        return rolesByProjectId.stream()
                .map(role -> new ParticipantDTO(
                                role.user().id(),
                                role.user().fullName(),
                                role.user().email()
                        )
                )
                .toList();
    }

    public List<ProjectDTO> getAllProjectsByUserId(String userId) {
        User owner = userRepository.getUserByUserId(userId);

        if (Objects.isNull(owner)) {
            throw new ResourceNotFoundException(USER_NOT_FOUND_EXCEPTION_MESSAGE);
        }

        List<Role> rolesByUserId = this.roleRepository.getAllRolesByUserId(userId);

        return rolesByUserId.stream()
                .map(role -> new ProjectDTO(
                                role.project().id(),
                                role.project().name(),
                                role.project().description(),
                                new OwnerDTO(role.user().id(), role.user().fullName())
                        )
                )
                .toList();
    }

    /**
     * This service can create a new project. It can also validate if any of the parameters is null
     * and if the user is trying to create a new project is a registered user in the DB.
     *
     * @param project Info provided for creating a new project.
     * @param userId  User creating a new project.
     * @return Data contract of the new project. From project: id, name, description;
     * from owner: owner id and owner full name.
     * @author Ruben Alvarado.
     */
    public ProjectDTO create(CreateProjectDTO project, String userId) {
        User owner = this.userRepository.getUserByUserId(userId);

        if (Objects.isNull(owner)) {
            throw new ResourceNotFoundException(USER_NOT_FOUND_EXCEPTION_MESSAGE);
        }

        CreateProjectValidator.validate(project);

        Project savedProject = this.projectRepository.saveProject(
                new Project(
                        null,
                        project.title(),
                        project.description()
                )
        );

        this.roleRepository.relateProjectToUser(owner.id(), savedProject.id(), RoleCatalogEnum.OWNER.getId());

        return new ProjectDTO(
                savedProject.id(),
                savedProject.name(),
                savedProject.description(),
                new OwnerDTO(
                        owner.id(),
                        owner.fullName()
                )
        );
    }
}
