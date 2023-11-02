package com.dinamitaexplosivainsana.nojira.application.repositories;

public interface RoleRepository {
    void relateProjectToUser(String userId, String projectId, int role);
}
