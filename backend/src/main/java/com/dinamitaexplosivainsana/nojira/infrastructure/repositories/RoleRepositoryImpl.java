package com.dinamitaexplosivainsana.nojira.infrastructure.repositories;

import com.dinamitaexplosivainsana.nojira.application.repositories.RoleRepository;
import com.dinamitaexplosivainsana.nojira.domain.models.Project;
import com.dinamitaexplosivainsana.nojira.domain.models.Role;
import com.dinamitaexplosivainsana.nojira.domain.models.RoleCatalog;
import com.dinamitaexplosivainsana.nojira.domain.models.User;
import com.dinamitaexplosivainsana.nojira.infrastructure.schemas.ProjectSchema;
import com.dinamitaexplosivainsana.nojira.infrastructure.schemas.RoleCatalogSchema;
import com.dinamitaexplosivainsana.nojira.infrastructure.schemas.RoleSchema;
import com.dinamitaexplosivainsana.nojira.infrastructure.schemas.UserSchema;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
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

    @Override
    public List<Role> getAllRolesByUserId(String userId) {
        List<RoleSchema> rolesSchema = this.roleRepository.findAllByUserId(userId);
        List<Role> roles = new ArrayList<>();

        for (RoleSchema roleSchema : rolesSchema) {
            Role role = new Role(roleSchema.getUser().getId(), roleSchema.getProject().getId(), roleSchema.getRoleCatalog().getId());
            roles.add(role);
        }
        return roles;
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
    @Override
    public Role findRoleBetweenUserAndProject(String userId, String projectId) {
        RoleSchema roleSchema = this.roleRepository.findByUserIdAndProjectId(userId, projectId);

        if(Objects.isNull(roleSchema)){
            return null;
        }

        return new Role(
                new RoleCatalog(
                        roleSchema.getRoleCatalog().getId(),
                        roleSchema.getRoleCatalog().getType()
                ),
                new User(
                        roleSchema.getUser().getId(),
                        roleSchema.getUser().getFullName(),
                        roleSchema.getUser().getEmail(),
                        roleSchema.getUser().getPassword()
                ),
                new Project(
                        roleSchema.getProject().getId(),
                        roleSchema.getProject().getName(),
                        roleSchema.getProject().getDescription()
                )
        );
    }

}
