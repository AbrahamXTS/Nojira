package com.dinamitaexplosivainsana.nojira.infrastructure.config;

import com.dinamitaexplosivainsana.nojira.application.repositories.*;
import com.dinamitaexplosivainsana.nojira.application.services.AuthService;
import com.dinamitaexplosivainsana.nojira.application.services.ProjectService;
import com.dinamitaexplosivainsana.nojira.application.services.TaskService;
import com.dinamitaexplosivainsana.nojira.application.utils.JWTUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BeansConfig {
    @Bean
    public AuthService authService(
            JWTUtils jwtUtils,
            PasswordEncoder passwordEncoder,
            UserRepository userRepository
    ) {
        return new AuthService(
                jwtUtils,
                passwordEncoder,
                userRepository
        );
    }

    @Bean
    public ProjectService projectService(
            ProjectRepository projectRepository,
            RoleRepository roleRepository,
            UserRepository userRepository
    ) {
        return new ProjectService(
                projectRepository,
                roleRepository,
                userRepository
        );
    }

    @Bean
    public TaskService taskService(
            ProjectRepository projectRepository,
            RoleRepository roleRepository,
            StatusCatalogRepository statusCatalogRepository,
            TaskRepository taskRepository
    ) {
        return new TaskService(
                projectRepository,
                roleRepository,
                statusCatalogRepository,
                taskRepository
        );
    }
}
