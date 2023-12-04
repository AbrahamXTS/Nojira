package com.dinamitaexplosivainsana.nojira.infrastructure.mappers;

import com.dinamitaexplosivainsana.nojira.domain.models.User;
import com.dinamitaexplosivainsana.nojira.infrastructure.schemas.UserSchema;
/**
 * Mapper class responsible for converting between {@link User} domain models
 * and {@link UserSchema} database schemas.
 */
public class UserMapper {
    /**
     * Private constructor to prevent instantiation of the mapper class.
     * All methods in this class are static, and it should not be instantiated.
     */
    private UserMapper() {
    }
/**
     * Maps a {@link UserSchema} object to a {@link User} domain model.
     *
     * @param userSchema The database schema representing a user.
     * @return A corresponding domain model representing the user.
     */
    public static User mapToModel(UserSchema userSchema) {
        return new User(
                userSchema.getId(),
                userSchema.getFullName(),
                userSchema.getEmail(),
                userSchema.getPassword()
        );
    }
/**
     * Maps a {@link User} domain model to a {@link UserSchema} database schema.
     *
     * @param user The domain model representing a user.
     * @return A corresponding database schema representing the user.
     */
    public static UserSchema mapToSchema(User user) {
        return new UserSchema(
                user.id(),
                user.fullName(),
                user.email(),
                user.password()
        );
    }
}
