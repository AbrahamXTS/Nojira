package com.dinamitaexplosivainsana.nojira.infrastructure.schemas;

import jakarta.persistence.*;
import lombok.*;
/**
 * Represents the JPA entity for role catalogs in the database.
 * Instances of this class are managed by JPA and stored in the "role_catalog" table.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "role_catalog")
public class RoleCatalogSchema {
    /**
     * The unique identifier for the role catalog.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * The type of the role catalog.
     * It must be unique and cannot be null.
     */

    @Column(nullable = false, unique = true)
    private String type;
}
