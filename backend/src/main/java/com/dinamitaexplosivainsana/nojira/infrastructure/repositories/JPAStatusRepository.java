package com.dinamitaexplosivainsana.nojira.infrastructure.repositories;

import com.dinamitaexplosivainsana.nojira.infrastructure.schemas.StatusCatalogSchema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * JPA repository interface for performing CRUD operations on {@link StatusCatalogSchema} entities.
 * Extends {@link JpaRepository} to benefit from Spring Data JPA features.
 */
@Repository
public interface JPAStatusRepository extends JpaRepository<StatusCatalogSchema, Integer> {
   /**
     * JPA repository interface for performing CRUD operations on {@link StatusCatalogSchema} entities.
     * Extends {@link JpaRepository} to benefit from Spring Data JPA features.
     */
    StatusCatalogSchema findByType(String type);
}