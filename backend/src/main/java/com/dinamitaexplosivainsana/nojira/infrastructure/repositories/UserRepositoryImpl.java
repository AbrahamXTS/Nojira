package com.dinamitaexplosivainsana.nojira.infrastructure.repositories;

import com.dinamitaexplosivainsana.nojira.application.repositories.UserRepository;
import com.dinamitaexplosivainsana.nojira.domain.models.User;
import com.dinamitaexplosivainsana.nojira.infrastructure.mappers.UserMapper;
import com.dinamitaexplosivainsana.nojira.infrastructure.schemas.UserSchema;
import org.springframework.stereotype.Repository;

import java.util.Objects;
/**
 * Implementation of the {@link UserRepository} interface using Spring Data JPA.
 * This repository handles CRUD operations on User entities.
 */
@Repository
public class UserRepositoryImpl implements UserRepository {
    private final JPAUserRepository userRepository;
/**
     * Constructor to initialize the repository with a JPAUserRepository instance.
     *
     * @param userRepository The JPA repository for User entities.
     */
    public UserRepositoryImpl(JPAUserRepository userRepository) {
        this.userRepository = userRepository;
    }
/**
     * Retrieves a User entity by its userId.
     *
     * @param userId The identifier of the User to retrieve.
     * @return The User entity if found, or null if not found.
     */
    @Override
    public User getUserByUserId(String userId) {
        UserSchema userSchema = this.userRepository.findById(userId)
                .orElse(null);

        if (Objects.isNull(userSchema)) {
            return null;
        }

        return UserMapper.mapToModel(userSchema);
    }
/**
     * Retrieves a User entity by its email address.
     *
     * @param email The email address of the User to retrieve.
     * @return The User entity if found, or null if not found.
     */
    @Override
    public User getUserByEmail(String email) {
        UserSchema userSchema = this.userRepository.findByEmail(email);

        if (Objects.isNull(userSchema)) {
            return null;
        }

        return UserMapper.mapToModel(userSchema);
    }
 /**
     * Saves a User entity.
     *
     * @param user The User entity to be saved.
     * @return The saved User entity.
     */
    @Override
    public User saveUser(User user) {
        UserSchema userSchema = this.userRepository.save(
                UserMapper.mapToSchema(user));

        return UserMapper.mapToModel(userSchema);
    }
}
