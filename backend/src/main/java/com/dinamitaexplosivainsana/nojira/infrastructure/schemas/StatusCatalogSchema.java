package com.dinamitaexplosivainsana.nojira.infrastructure.schemas;

import jakarta.persistence.*;
import lombok.*;
/**
 * Represents the JPA entity for status catalogs in the database.
 * Instances of this class are managed by JPA and stored in the "status_catalog" table.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "status_catalog")
public class StatusCatalogSchema {
    /**
     * The unique identifier for the status catalog.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
/**
     * The type of the status catalog.
     * It cannot be null.
     */
    @Column(nullable = false)
    private String type;
}
