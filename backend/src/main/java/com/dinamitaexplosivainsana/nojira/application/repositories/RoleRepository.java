package com.dinamitaexplosivainsana.nojira.application.repositories;

import com.dinamitaexplosivainsana.nojira.domain.models.Role;

import java.util.List;

public interface RoleRepository {
    void relateProjectToUser(String userId, String projectId, int role);

    List<Role> getAllRolesByProjectId(String projectId);

    List<Role> getAllRolesByUserId(String userId);

    Role findRoleBetweenUserAndProject(String userId, String projectId);
}
