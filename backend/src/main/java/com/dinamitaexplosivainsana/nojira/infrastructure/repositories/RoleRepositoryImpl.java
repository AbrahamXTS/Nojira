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
    public void relateProjectToUser(String userId, String projectId, int roleId) {

        this.roleRepository.save(
                RoleSchema.builder()
                        .user(this.getUserSchema(userId))
                        .project(this.getProjectSchema(projectId))
                        .roleCatalog(this.getRoleCatalogSchema(roleId))
                        .build()
        );
    }

    private UserSchema getUserSchema(String userId) {
        UserSchema userSchema = this.userRepository.findById(userId).orElse(null);

        return userSchema;
    }

    private ProjectSchema getProjectSchema(String projectId) {
        ProjectSchema projectSchema = this.projectRepository.findById(projectId).orElse(null);

        return projectSchema;
    }

    private RoleCatalogSchema getRoleCatalogSchema(int roleId) {
        RoleCatalogSchema roleCatalogSchema = this.roleCatalogRepository.findById(String.valueOf(roleId))
                .orElse(null);

        return roleCatalogSchema;
    }
}
