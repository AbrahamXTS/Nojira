package com.dinamitaexplosivainsana.nojira.application.repositories;

import com.dinamitaexplosivainsana.nojira.domain.models.Role;

public interface RoleRepository {

    Role findRoleBetweenUserAndProject(String userId, String projectId);
}
