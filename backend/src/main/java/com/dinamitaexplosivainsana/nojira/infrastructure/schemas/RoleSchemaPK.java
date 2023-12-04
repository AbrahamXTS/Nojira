package com.dinamitaexplosivainsana.nojira.infrastructure.schemas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
/**
 * Represents the composite primary key for the RoleSchema entity.
 * This key is composed of UserSchema, ProjectSchema, and RoleCatalogSchema instances.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class RoleSchemaPK implements Serializable {
    private transient UserSchema user;
    private transient ProjectSchema project;
    private transient RoleCatalogSchema roleCatalog;
}
