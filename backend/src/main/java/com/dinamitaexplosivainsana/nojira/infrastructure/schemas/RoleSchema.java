package com.dinamitaexplosivainsana.nojira.infrastructure.schemas;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "role")
@IdClass(RoleSchemaPK.class)
public class RoleSchema {
    @Id
    @ManyToOne
    @JoinColumn(nullable = false)
    private UserSchema user;

    @Id
    @ManyToOne
    @JoinColumn(nullable = false)
    private ProjectSchema project;

    @Id
    @ManyToOne
    @JoinColumn(nullable = false)
    private RoleCatalogSchema roleCatalog;
}