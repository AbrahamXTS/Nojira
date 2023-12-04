package com.dinamitaexplosivainsana.nojira.infrastructure.schemas;

import jakarta.persistence.*;
import lombok.*;
/**
 * Represents the JPA entity for projects in the database.
 * Instances of this class are managed by JPA and stored in the "project" table.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "project")
public class ProjectSchema {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;
}
