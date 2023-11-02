package com.dinamitaexplosivainsana.nojira.infrastructure.repositories;

import com.dinamitaexplosivainsana.nojira.infrastructure.schemas.RoleSchema;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPARoleRepository extends JpaRepository<RoleSchema, String> {
}
