package com.dinamitaexplosivainsana.nojira.infrastructure.repositories;

import com.dinamitaexplosivainsana.nojira.infrastructure.schemas.ProjectSchema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * JPA repository interface for performing CRUD operations on {@link ProjectSchema} entities.
 * Extends {@link JpaRepository} to benefit from Spring Data JPA features.
 */
@Repository
public interface JPAProjectRepository extends JpaRepository<ProjectSchema, String> {
}