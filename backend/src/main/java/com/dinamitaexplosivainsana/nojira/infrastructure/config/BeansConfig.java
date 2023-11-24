package com.dinamitaexplosivainsana.nojira.infrastructure.config;
import com.dinamitaexplosivainsana.nojira.application.repositories.*;
import com.dinamitaexplosivainsana.nojira.application.services.*;

import com.dinamitaexplosivainsana.nojira.application.repositories.TaskRepository;
import com.dinamitaexplosivainsana.nojira.application.repositories.UserRepository;
import com.dinamitaexplosivainsana.nojira.application.services.AuthService;

import com.dinamitaexplosivainsana.nojira.application.utils.JWTUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BeansConfig {
    @Bean
    public AuthService authService(JWTUtils jwtUtils, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        return new AuthService(jwtUtils, passwordEncoder, userRepository);
    }

    @Bean
    public ProjectService projectService(
            ProjectRepository projectRepository,
            RoleRepository roleRepository,
            StatusRepository statusRepository,
            TaskRepository taskRepository,
            UserRepository userRepository
    ) {
        return new ProjectService(projectRepository, roleRepository, statusRepository, taskRepository, userRepository);
    }

    @Bean
    public TaskTrackingService taskTrackingService(TaskRepository taskRepository){
        return new TaskTrackingService(taskRepository);

    }

    @Bean
    public TaskService taskService(TaskRepository taskRepository, RoleRepository roleRepository){
        return new TaskService(taskRepository, roleRepository);
    }

    @Bean
    public UpdateTaskService updateTaskService(TaskRepository taskRepository, RoleRepository roleRepository) {
        return new UpdateTaskService(taskRepository, roleRepository);
    }

    @Bean
    public DeleteTaskService deleteTaskService(TaskRepository taskRepository) {
        return new DeleteTaskService(taskRepository);
    }

}
