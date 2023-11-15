package com.dinamitaexplosivainsana.nojira.application.services;

import com.dinamitaexplosivainsana.nojira.application.repositories.*;
import com.dinamitaexplosivainsana.nojira.domain.dto.*;
import com.dinamitaexplosivainsana.nojira.domain.exceptions.InvalidProjectException;
import com.dinamitaexplosivainsana.nojira.domain.exceptions.InvalidUserException;
import com.dinamitaexplosivainsana.nojira.domain.models.*;
import com.dinamitaexplosivainsana.nojira.domain.validators.GetProjectValidator;

import java.util.ArrayList;
import java.util.List;

public class ProjectService {

    private final RoleRepository roleRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final StatusRepository statusRepository;


    public ProjectService(RoleRepository roleRepository, ProjectRepository projectRepository, UserRepository userRepository, TaskRepository taskRepository, StatusRepository statusRepository) {
        this.roleRepository = roleRepository;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.statusRepository = statusRepository;
    }

    public List<ProjectDTO> getAllProjectsByUserId(String userId) {
        GetProjectValidator.validate(userId);
        List<ProjectDTO> projectsDTO = new ArrayList<>();
        List<Project> projects = new ArrayList<>();

        // Esto podría ser un stream pero primero ver si funciona 🫨

        List<Role> roles = roleRepository.getAllRolesByUserId(userId);
        for (Role role : roles) {
            if (role.roleId() == 1) {
                Project project = projectRepository.getProjectByProjectId(role.projectId());
                projects.add(project);
            }
        }
        User user = userRepository.getUserByUserId(userId);
        OwnerDTO owner = new OwnerDTO(user.id(), user.fullName());

        if (projects.isEmpty()) {
            throw new InvalidUserException("Usuario no reconocida (cambiar esto a constante?)");
        }

        for (Project project : projects) {
            ProjectDTO projectDTO = new ProjectDTO(project.id(), project.name(), project.description(), owner);
            projectsDTO.add(projectDTO);
        }
        return projectsDTO;
    }

    public List<TaskDTO> getAllTasksPerProject(String projectId)  {
        GetProjectValidator.validate(projectId);
        List<Task> tasks = taskRepository.getAllTasksByProjectId(projectId);
        List<TaskDTO> taskDTOS = new ArrayList<>();

        if(tasks.isEmpty()){
            throw new InvalidProjectException("El proyecto no se encuentra registrado (mejorar y cambiar a constante?)");
        }

        for(Task task: tasks){
            Status status = statusRepository.getStatusByStatusId(task.statusId());
            StatusDTO statusDTO = new StatusDTO(status.type());
            TimesDTO timeDTO = new TimesDTO(task.estimated(),task.total());
            User user = userRepository.getUserByUserId(task.userId());
            AssignedDTO assignedDTO = new AssignedDTO(task.userId(),user.fullName());
            TaskDTO taskDTO = new TaskDTO(task.id(),task.title(),task.description(),statusDTO,timeDTO,assignedDTO);
            taskDTOS.add(taskDTO);
        }

        return taskDTOS;
    }



}
