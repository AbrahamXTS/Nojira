package com.dinamitaexplosivainsana.nojira.infrastructure.repositories;

import com.dinamitaexplosivainsana.nojira.infrastructure.schemas.RoleCatalogSchema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * JPA repository interface for performing CRUD operations on {@link RoleCatalogSchema} entities.
 * Extends {@link JpaRepository} to benefit from Spring Data JPA features.
 */
@Repository
public interface JPARoleCatalogRepository extends JpaRepository<RoleCatalogSchema, Integer> {
}
