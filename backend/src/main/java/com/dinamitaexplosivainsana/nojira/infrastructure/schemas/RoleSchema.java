package com.dinamitaexplosivainsana.nojira.infrastructure.schemas;

import jakarta.persistence.*;
import lombok.*;
/**
 * Represents the JPA entity for roles in the database.
 * Instances of this class are managed by JPA and stored in the "role" table.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "role")
@IdClass(RoleSchemaPK.class)
public class RoleSchema {
    /**
     * The user associated with the role.
     */
    @Id
    @ManyToOne
    @JoinColumn(nullable = false)
    private UserSchema user;
/**
     * The project associated with the role.
     */
    @Id
    @ManyToOne
    @JoinColumn(nullable = false)
    private ProjectSchema project;
/**
     * The role catalog associated with the role.
     */
    @Id
    @ManyToOne
    @JoinColumn(nullable = false)
    private RoleCatalogSchema roleCatalog;
}