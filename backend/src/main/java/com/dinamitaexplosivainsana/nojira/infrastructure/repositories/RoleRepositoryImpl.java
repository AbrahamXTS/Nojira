package com.dinamitaexplosivainsana.nojira.infrastructure.repositories;

import com.dinamitaexplosivainsana.nojira.application.repositories.RoleRepository;
import com.dinamitaexplosivainsana.nojira.infrastructure.schemas.ProjectSchema;
import com.dinamitaexplosivainsana.nojira.infrastructure.schemas.RoleCatalogSchema;
import com.dinamitaexplosivainsana.nojira.infrastructure.schemas.RoleSchema;
import com.dinamitaexplosivainsana.nojira.infrastructure.schemas.UserSchema;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class RoleRepositoryImpl implements RoleRepository {
    private final JPARoleRepository roleRepository;
    private final JPAUserRepository userRepository;
    private final JPAProjectRepository projectRepository;
    private final JPARoleCatalogRepository roleCatalogRepository;

    public RoleRepositoryImpl(JPARoleRepository roleRepository,
                              JPAUserRepository userRepository,
                              JPAProjectRepository projectRepository,
                              JPARoleCatalogRepository roleCatalogRepository
    ) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
        this.roleCatalogRepository = roleCatalogRepository;
    }

    @Override
    public void relateProjectToUser(String userId, String projectId, int role) {

        this.roleRepository.save(
                RoleSchema.builder()
                        .user(this.getUserSchema(userId))
                        .project(this.getProjectSchema(projectId))
                        .roleCatalog(this.getRoleCatalogSchema(role))
                        .build()
        );
    }

    private UserSchema getUserSchema(String userId) {
        UserSchema userSchema = this.userRepository.findById(userId).orElse(null);

        if (Objects.isNull(userSchema)) {
            return null;
        }

        return userSchema;
    }

    private ProjectSchema getProjectSchema(String projectId) {
        ProjectSchema projectSchema = this.projectRepository.findById(projectId).orElse(null);

        if (Objects.isNull(projectSchema)) {
            return null;
        }

        return projectSchema;
    }

    private RoleCatalogSchema getRoleCatalogSchema(int role) {
        RoleCatalogSchema roleCatalogSchema = this.roleCatalogRepository.findById(String.valueOf(role))
                .orElse(null);

        if (Objects.isNull(roleCatalogSchema)){
            return null;
        }

        return roleCatalogSchema;
    }
}
