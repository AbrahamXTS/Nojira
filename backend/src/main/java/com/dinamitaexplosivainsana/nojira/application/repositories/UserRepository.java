package com.dinamitaexplosivainsana.nojira.application.repositories;

import com.dinamitaexplosivainsana.nojira.domain.models.User;
/**
 * Interface that defines operations to access and manipulate user information in the system.
 */
public interface UserRepository {
    /**
     * Retrieves a {@code User} object based on its unique identifier.
     *
     * @param userId The unique identifier of the user to retrieve.
     * @return The {@code User} object corresponding to the provided unique identifier.
     */
    User getUserByUserId(String userId);
     /**
     * Retrieves a {@code User} object based on its email address.
     *
     * @param email The email address of the user to retrieve.
     * @return The {@code User} object corresponding to the provided email address.
     */
    User getUserByEmail(String email);
     /**
     * Saves a new user in the system.
     *
     * @param user The {@code User} object to be saved in the system.
     * @return The {@code User} object that has been saved in the system.
     */
    User saveUser(User user);
}
