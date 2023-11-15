package com.dinamitaexplosivainsana.nojira.infrastructure.config;

import com.dinamitaexplosivainsana.nojira.application.repositories.*;
import com.dinamitaexplosivainsana.nojira.application.services.AuthService;
import com.dinamitaexplosivainsana.nojira.application.services.ProjectService;
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
    public ProjectService projectService(RoleRepository roleRepository, ProjectRepository projectRepository, UserRepository userRepository, TaskRepository taskRepository, StatusRepository statusRepository ) {
        return new ProjectService( roleRepository,projectRepository, userRepository,taskRepository,statusRepository);
    }
}
