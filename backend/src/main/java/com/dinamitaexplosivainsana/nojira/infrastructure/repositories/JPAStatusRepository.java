package com.dinamitaexplosivainsana.nojira.infrastructure.repositories;

import com.dinamitaexplosivainsana.nojira.infrastructure.schemas.StatusCatalogSchema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JPAStatusRepository extends JpaRepository<StatusCatalogSchema,Integer> {
    Optional<StatusCatalogSchema> findById(Integer id);
}
