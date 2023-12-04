package com.dinamitaexplosivainsana.nojira.infrastructure.repositories;

import com.dinamitaexplosivainsana.nojira.application.repositories.RoleRepository;
import com.dinamitaexplosivainsana.nojira.domain.models.Role;
import com.dinamitaexplosivainsana.nojira.infrastructure.mappers.RoleMapper;
import com.dinamitaexplosivainsana.nojira.infrastructure.schemas.RoleSchema;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
/**
 * Implementation of the {@link RoleRepository} interface using Spring Data JPA.
 * This repository handles CRUD operations on Role entities and relationships between users and projects.
 */
@Repository
public class RoleRepositoryImpl implements RoleRepository {
    private final JPAProjectRepository projectRepository;
    private final JPARoleCatalogRepository roleCatalogRepository;
    private final JPARoleRepository roleRepository;
    private final JPAUserRepository userRepository;
/**
     * Constructor to initialize the repository with JPA repositories for Project, RoleCatalog, Role, and User entities.
     *
     * @param projectRepository      The JPA repository for Project entities.
     * @param roleCatalogRepository  The JPA repository for RoleCatalog entities.
     * @param roleRepository         The JPA repository for Role entities.
     * @param userRepository         The JPA repository for User entities.
     */
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
/**
     * Finds a Role entity between a user and a project.
     *
     * @param userId    The identifier of the user.
     * @param projectId The identifier of the project.
     * @return The Role entity if found, or null if not found.
     */
    @Override
    public Role findRoleBetweenUserAndProject(String userId, String projectId) {
        RoleSchema roleSchema = this.roleRepository.findByUserIdAndProjectId(userId, projectId);

        if (Objects.isNull(roleSchema)) {
            return null;
        }

        return RoleMapper.mapToModel(roleSchema);
    }
/**
     * Gets all Role entities associated with a project.
     *
     * @param projectId The identifier of the project.
     * @return A list of Role entities.
     */
    @Override
    public List<Role> getAllRolesByProjectId(String projectId) {
        List<RoleSchema> rolesSchema = this.roleRepository.findAllByProjectId(projectId);

        return rolesSchema.stream()
                .map(RoleMapper::mapToModel)
                .toList();
    }
/**
     * Gets all Role entities associated with a user.
     *
     * @param userId The identifier of the user.
     * @return A list of Role entities.
     */
    @Override
    public List<Role> getAllRolesByUserId(String userId) {
        List<RoleSchema> rolesSchema = this.roleRepository.findAllByUserId(userId);

        return rolesSchema.stream()
                .map(RoleMapper::mapToModel)
                .toList();
    }
/**
     * Relates a project to a user by creating a new Role entity.
     *
     * @param userId    The identifier of the user.
     * @param projectId The identifier of the project.
     * @param roleId    The identifier of the role in the role catalog.
     */
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
