package com.dinamitaexplosivainsana.nojira.infrastructure.repositories;

import com.dinamitaexplosivainsana.nojira.infrastructure.schemas.RoleCatalogSchema;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPARoleCatalogRepository extends JpaRepository<RoleCatalogSchema, String> {
}
