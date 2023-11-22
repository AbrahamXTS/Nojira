package com.dinamitaexplosivainsana.nojira.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dinamitaexplosivainsana.nojira.infrastructure.schemas.RoleCatalogSchema;

@Repository
public interface JPARoleCatalogRepository extends JpaRepository<RoleCatalogSchema, Integer> {
}