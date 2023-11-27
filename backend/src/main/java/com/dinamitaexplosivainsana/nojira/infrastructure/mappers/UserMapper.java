package com.dinamitaexplosivainsana.nojira.infrastructure.mappers;

import com.dinamitaexplosivainsana.nojira.domain.models.User;
import com.dinamitaexplosivainsana.nojira.infrastructure.schemas.UserSchema;

public class UserMapper {
    private UserMapper() {
    }

    public static User mapToModel(UserSchema userSchema) {
        return new User(
                userSchema.getId(),
                userSchema.getFullName(),
                userSchema.getEmail(),
                userSchema.getPassword()
        );
    }

    public static UserSchema mapToSchema(User user) {
        return new UserSchema(
                user.id(),
                user.fullName(),
                user.email(),
                user.password()
        );
    }
}
