package com.dinamitaexplosivainsana.nojira.infrastructure.config;

import com.dinamitaexplosivainsana.nojira.application.repositories.*;
import com.dinamitaexplosivainsana.nojira.application.services.AuthService;
import com.dinamitaexplosivainsana.nojira.application.services.ProjectService;
import com.dinamitaexplosivainsana.nojira.application.services.TaskService;
import com.dinamitaexplosivainsana.nojira.application.utils.JWTUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
/**
 * This class provides configuration for the beans used in the application.
 * It uses the @Configuration annotation to indicate that it is a source of bean definitions.
 */
@Configuration
public class BeansConfig {
        /**
     * Configures the AuthService bean.
     *
     * @param jwtUtils JWT utility class.
     * @param passwordEncoder Password encoder.
     * @param userRepository User repository.
     * @return An instance of AuthService.
     */
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
/**
     * Configures the ProjectService bean.
     *
     * @param projectRepository Project repository.
     * @param roleRepository Role repository.
     * @param userRepository User repository.
     * @return An instance of ProjectService.
     */
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
/**
     * Configures the TaskService bean.
     *
     * @param projectRepository Project repository.
     * @param roleRepository Role repository.
     * @param statusCatalogRepository Status catalog repository.
     * @param taskRepository Task repository.
     * @return An instance of TaskService.
     */
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
