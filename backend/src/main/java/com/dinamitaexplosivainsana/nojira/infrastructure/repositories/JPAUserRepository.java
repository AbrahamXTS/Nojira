package com.dinamitaexplosivainsana.nojira.infrastructure.repositories;

import com.dinamitaexplosivainsana.nojira.infrastructure.schemas.UserSchema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * JPA repository interface for performing CRUD operations on {@link UserSchema} entities.
 * Extends {@link JpaRepository} to benefit from Spring Data JPA features.
 */
@Repository
public interface JPAUserRepository extends JpaRepository<UserSchema, String> {
    /**
     * Retrieves a user entity based on the specified email.
     *
     * @param email The email address of the user.
     * @return The user entity associated with the specified email.
     */
    UserSchema findByEmail(String email);
}
