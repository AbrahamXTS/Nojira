package com.dinamitaexplosivainsana.nojira.infrastructure.repositories;

import com.dinamitaexplosivainsana.nojira.application.repositories.RoleRepository;
import com.dinamitaexplosivainsana.nojira.domain.models.Role;
import com.dinamitaexplosivainsana.nojira.infrastructure.mappers.RoleMapper;
import com.dinamitaexplosivainsana.nojira.infrastructure.schemas.RoleSchema;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class RoleRepositoryImpl implements RoleRepository {
    private final JPAProjectRepository projectRepository;
    private final JPARoleCatalogRepository roleCatalogRepository;
    private final JPARoleRepository roleRepository;
    private final JPAUserRepository userRepository;

    public RoleRepositoryImpl(
            JPAProjectRepository projectRepository,
            JPARoleCatalogRepository roleCatalogRepository,
            JPARoleRepository roleRepository,
            JPAUserRepository userRepository
    ) {
        this.projectRepository = projectRepository;
        this.roleCatalogRepository = roleCatalogRepository;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Role findRoleBetweenUserAndProject(String userId, String projectId) {
        RoleSchema roleSchema = this.roleRepository.findByUserIdAndProjectId(userId, projectId);

        if (Objects.isNull(roleSchema)) {
            return null;
        }

        return RoleMapper.mapToModel(roleSchema);
    }

    @Override
    public List<Role> getAllRolesByProjectId(String projectId) {
        List<RoleSchema> rolesSchema = this.roleRepository.findAllByProjectId(projectId);

        return rolesSchema.stream()
                .map(RoleMapper::mapToModel)
                .toList();
    }

    @Override
    public List<Role> getAllRolesByUserId(String userId) {
        List<RoleSchema> rolesSchema = this.roleRepository.findAllByUserId(userId);

        return rolesSchema.stream()
                .map(RoleMapper::mapToModel)
                .toList();
    }

    @Override
    public void relateProjectToUser(String userId, String projectId, int roleId) {
        this.roleRepository.save(
                RoleSchema.builder()
                        .user(this.userRepository.findById(userId).orElse(null))
                        .project(this.projectRepository.findById(projectId).orElse(null))
                        .roleCatalog(this.roleCatalogRepository.findById(roleId).orElse(null))
                        .build()
        );
    }
}
