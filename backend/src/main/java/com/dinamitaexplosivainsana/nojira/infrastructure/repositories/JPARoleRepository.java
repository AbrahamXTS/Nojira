package com.dinamitaexplosivainsana.nojira.infrastructure.repositories;

import com.dinamitaexplosivainsana.nojira.infrastructure.schemas.RoleSchema;
import com.dinamitaexplosivainsana.nojira.infrastructure.schemas.RoleSchemaPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * JPA repository interface for performing CRUD operations on {@link RoleSchema} entities
 * with a composite primary key of type {@link RoleSchemaPK}.
 * Extends {@link JpaRepository} to benefit from Spring Data JPA features.
 */
@Repository
public interface JPARoleRepository extends JpaRepository<RoleSchema, RoleSchemaPK> {
     /**
     * Retrieves a list of roles based on the specified project ID.
     *
     * @param projectId The ID of the project.
     * @return A list of roles associated with the specified project.
     */
    List<RoleSchema> findAllByProjectId(String projectId);
 /**
     * Retrieves a list of roles based on the specified user ID.
     *
     * @param userId The ID of the user.
     * @return A list of roles associated with the specified user.
     */
    List<RoleSchema> findAllByUserId(String userId);
/**
     * Retrieves a role based on the specified user ID and project ID.
     *
     * @param userId    The ID of the user.
     * @param projectId The ID of the project.
     * @return The role associated with the specified user and project.
     */
    RoleSchema findByUserIdAndProjectId(String userId, String projectId);
}