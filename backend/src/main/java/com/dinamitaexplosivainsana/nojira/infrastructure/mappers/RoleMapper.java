package com.dinamitaexplosivainsana.nojira.infrastructure.mappers;

import com.dinamitaexplosivainsana.nojira.domain.models.Role;
import com.dinamitaexplosivainsana.nojira.infrastructure.schemas.RoleSchema;

public class RoleMapper {
    private RoleMapper() {
    }

    public static Role mapToModel(RoleSchema roleSchema) {
        return new Role(
                UserMapper.mapToModel(roleSchema.getUser()),
                ProjectMapper.mapToModel(roleSchema.getProject()),
                RoleCatalogMapper.mapToModel(roleSchema.getRoleCatalog())
        );
    }

    public static RoleSchema mapToSchema(Role role) {
        return new RoleSchema(
                UserMapper.mapToSchema(role.user()),
                ProjectMapper.mapToSchema(role.project()),
                RoleCatalogMapper.mapToSchema(role.roleCatalog())
        );
    }
}
