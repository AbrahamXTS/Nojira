package com.dinamitaexplosivainsana.nojira.application.repositories;

import com.dinamitaexplosivainsana.nojira.infrastructure.schemas.RoleSchema;

public interface RoleRepository {
    void relateProjectToUser(String userId, String projectId, int role);
}
