package com.dinamitaexplosivainsana.nojira.domain.models;


public record Role(
        String userId,
        String projectId,
        Integer roleId,
        RoleCatalog roleCatalog,
        User user,
        Project project
) {
    public Role(String userId, String projectId, Integer roleId) {
        this(userId, projectId, roleId, null, null, null);
    }

    public Role(RoleCatalog role,User user, Project project) {
        this(null, null, null,role,user,project);
    }

}


