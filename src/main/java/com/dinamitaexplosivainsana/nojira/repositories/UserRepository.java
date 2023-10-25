package com.dinamitaexplosivainsana.nojira.repositories;

import com.dinamitaexplosivainsana.nojira.schemas.UserSchema;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserSchema, String> {
    UserSchema findByEmail(String email);
}
