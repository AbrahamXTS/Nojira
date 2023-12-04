package com.dinamitaexplosivainsana.nojira.infrastructure.mappers;

import com.dinamitaexplosivainsana.nojira.domain.models.Role;
import com.dinamitaexplosivainsana.nojira.infrastructure.schemas.RoleSchema;
/**
 * Mapper class responsible for converting between {@link Role} domain models
 * and {@link RoleSchema} database schemas.
 */
public class RoleMapper {
    /**
     * Private constructor to prevent instantiation of the mapper class.
     * All methods in this class are static, and it should not be instantiated.
     */
    private RoleMapper() {
    }
 /**
     * Maps a {@link RoleSchema} object to a {@link Role} domain model.
     *
     * @param roleSchema The database schema representing a role.
     * @return A corresponding domain model representing the role.
     */
    public static Role mapToModel(RoleSchema roleSchema) {
        return new Role(
                UserMapper.mapToModel(roleSchema.getUser()),
                ProjectMapper.mapToModel(roleSchema.getProject()),
                RoleCatalogMapper.mapToModel(roleSchema.getRoleCatalog())
        );
    }
 /**
     * Maps a {@link Role} domain model to a {@link RoleSchema} database schema.
     *
     * @param role The domain model representing a role.
     * @return A corresponding database schema representing the role.
     */
    public static RoleSchema mapToSchema(Role role) {
        return new RoleSchema(
                UserMapper.mapToSchema(role.user()),
                ProjectMapper.mapToSchema(role.project()),
                RoleCatalogMapper.mapToSchema(role.roleCatalog())
        );
    }
}
