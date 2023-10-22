package com.dinamitaexplosivainsana.nojira.schemas;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "role")
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
    private RoleCatalogSchema role;
}
