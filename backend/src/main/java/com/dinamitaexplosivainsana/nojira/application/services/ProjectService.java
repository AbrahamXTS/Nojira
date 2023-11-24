package com.dinamitaexplosivainsana.nojira.application.services;

import com.dinamitaexplosivainsana.nojira.application.repositories.*;
import com.dinamitaexplosivainsana.nojira.domain.dto.*;
import com.dinamitaexplosivainsana.nojira.domain.exceptions.InvalidProjectException;
import com.dinamitaexplosivainsana.nojira.domain.exceptions.InvalidUserException;
import com.dinamitaexplosivainsana.nojira.domain.exceptions.UnauthorizedUserException;
import com.dinamitaexplosivainsana.nojira.domain.models.*;
import com.dinamitaexplosivainsana.nojira.domain.validators.CreateProjectValidator;
import com.dinamitaexplosivainsana.nojira.domain.validators.ProjectValidator;
import com.dinamitaexplosivainsana.nojira.domain.validators.UserManagerValidator;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.dinamitaexplosivainsana.nojira.domain.config.Constants.*;

public class ProjectService {
    private final ProjectRepository projectRepository;
    private final RoleRepository roleRepository;
    private final StatusRepository statusRepository;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public ProjectService(
        ProjectRepository projectRepository,
        RoleRepository roleRepository,
        StatusRepository statusRepository,
        TaskRepository taskRepository,
        UserRepository userRepository
    ) {
        this.projectRepository = projectRepository;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.statusRepository = statusRepository;
        this.taskRepository = taskRepository;
    }

    /**
     * This service can create a new project. It can also validate if any of the parameters is null
     * and if the user is trying to create a new project is a registered user in the DB.
     *
     * @author Ruben Alvarado.
     * @param project Info provided for creating a new project.
     * @param userId User creating a new project.
     * @return Data contract of the new project. From project: id, name, description;
     *         from owner: owner id and owner full name.
     */
    public CreatedProjectManagementDTO create(CreateProjectDTO project, String userId) {
        User userOwner = this.userRepository.getUserByUserId(userId);

        UserManagerValidator.validate(userOwner);
        CreateProjectValidator.validate(project);

        Project savedProject = this.projectRepository.saveProject(
                new Project(
                        null,
                        project.projectName(),
                        project.description()
                )
        );

        // 1 role, is defined for project owners
        this.roleRepository.relateProjectToUser(userOwner.id(), savedProject.id(), RoleCatalogEnum.OWNER.getId());

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

    public List<ProjectDTO> getAllProjectsByUserId(String userId) {
        ProjectValidator.validate(userId);

        User user = userRepository.getUserByUserId(userId);

        if (Objects.isNull(user)) {
            throw new InvalidUserException(ERROR_USER_NOT_RECOGNIZED_MESSAGE);
        }

        List<Project> projects = getProjectsByUserId(userId);

        OwnerDTO owner = createOwnerDTO(userId);

        return projects.stream()
                .map(project -> new ProjectDTO(project.id(), project.name(), project.description(), owner))
                .collect(Collectors.toList());
    }

    private List<Project> getProjectsByUserId(String userId) {
        return roleRepository.getAllRolesByUserId(userId).stream()
                .filter(role -> role.roleId() == 1)
                .map(role -> projectRepository.getProjectByProjectId(role.projectId()))
                .collect(Collectors.toList());
    }

    private OwnerDTO createOwnerDTO(String userId) {
        User user = userRepository.getUserByUserId(userId);
        return new OwnerDTO(user.id(), user.fullName());
    }

    public List<ProjectInfoDTO> getAllTasksPerProject(String userId, String projectId) {
        ProjectValidator.validate(projectId);
        ProjectValidator.validate(userId);
        Project project = projectRepository.getProjectByProjectId(projectId);
        List<Role> roles = roleRepository.getAllRolesByUserId(userId);
        User user = userRepository.getUserByUserId(userId);

        if (Objects.isNull(user)) {
            throw new InvalidUserException(ERROR_USER_NOT_RECOGNIZED_MESSAGE);
        }

        if (Objects.isNull(project)) {
            throw new InvalidProjectException(ERROR_PROJECT_NOT_REGISTERED_MESSAGE);
        }

        boolean userIsInProject = roles.stream()
                .anyMatch(role -> Objects.equals(role.projectId(), projectId));


        if (!userIsInProject) {
            throw new UnauthorizedUserException(USER_IS_NOT_IN_PROJECT_MESSAGE);
        }

        List<Task> tasks = taskRepository.getAllTasksByProjectId(projectId);

        List<TaskDTO> taskDTOs = tasks.stream()
                .map(this::mapTaskToDTO)
                .collect(Collectors.toList());

        return Collections.singletonList(new ProjectInfoDTO(projectId, project.name(), taskDTOs));
    }

    private TaskDTO mapTaskToDTO(Task task) {
        Status status = statusRepository.getStatusByStatusId(task.statusId());

        StatusDTO statusDTO = new StatusDTO(status.type());

        TimesDTO timeDTO = new TimesDTO(task.timeEstimatedInMinutes(), task.timeUsedInMinutes());

        User user = userRepository.getUserByUserId(task.userId());

        AssignedDTO assignedDTO = new AssignedDTO(task.userId(), user.fullName());

        return new TaskDTO(task.id(), task.title(), task.description(), statusDTO, timeDTO, assignedDTO);
    }
}
