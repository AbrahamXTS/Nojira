package com.dinamitaexplosivainsana.nojira.application.repositories;

import com.dinamitaexplosivainsana.nojira.domain.models.Role;

import java.util.List;
/**
 * This interface defines methods to access and manipulate data related to roles in a project.
 * It provides operations such as assigning a role to a user in a project, retrieving all roles
 * associated with a project or user, and finding a specific role between a user and a project.
 */
public interface RoleRepository {
    /**
     * Assigns a role to a user in a specific project.
     *
     * @param userId    The unique identifier of the user.
     * @param projectId The unique identifier of the project.
     * @param role      The code or identifier of the role to be assigned.
     */
    void relateProjectToUser(String userId, String projectId, int role);
     /**
     * Retrieves all roles associated with a specific project.
     *
     * @param projectId The unique identifier of the project.
     * @return A list of Role objects associated with the project.
     */
    List<Role> getAllRolesByProjectId(String projectId);
    /**
     * Retrieves all roles associated with a specific user.
     *
     * @param userId The unique identifier of the user.
     * @return A list of Role objects associated with the user.
     */
    List<Role> getAllRolesByUserId(String userId);
     /**
     * Finds a specific role between a user and a project.
     *
     * @param userId    The unique identifier of the user.
     * @param projectId The unique identifier of the project.
     * @return The Role object representing the user's role in the project, or null if not found.
     */
    Role findRoleBetweenUserAndProject(String userId, String projectId);
}
