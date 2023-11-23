package com.dinamitaexplosivainsana.nojira.infrastructure.repositories;

import com.dinamitaexplosivainsana.nojira.application.repositories.UserRepository;
import com.dinamitaexplosivainsana.nojira.domain.models.User;
import com.dinamitaexplosivainsana.nojira.infrastructure.schemas.UserSchema;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final JPAUserRepository userRepository;

    public UserRepositoryImpl(JPAUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserByUserId(String userId) {
        UserSchema userSchema = this.userRepository.findById(userId)
                .orElse(null);

        if (Objects.isNull(userSchema)) {
            return null;
        }

        return new User(
                userSchema.getId(),
                userSchema.getFullName(),
                userSchema.getEmail(),
                userSchema.getPassword()
        );
    }

    @Override
    public User getUserByEmail(String email) {
        UserSchema userSchema = this.userRepository.findByEmail(email);

        if (Objects.isNull(userSchema)) {
            return null;
        }

        return new User(
                userSchema.getId(),
                userSchema.getFullName(),
                userSchema.getEmail(),
                userSchema.getPassword()
        );
    }

    @Override
    public User saveUser(User user) {
        UserSchema userSchema = this.userRepository.save(
                UserSchema.builder()
                        .email(user.email())
                        .fullName(user.fullName())
                        .password(user.password())
                        .build()
        );

        return new User(
                userSchema.getId(),
                userSchema.getFullName(),
                userSchema.getEmail(),
                userSchema.getPassword()
        );
    }

    @Override
    public User deleteUserByUserId(String userId) {
        UserSchema userSchema = this.userRepository.findById(userId)
                .orElse(null);

        if (Objects.isNull(userSchema)) {
            return null;
        }

        this.userRepository.delete(userSchema);

        return new User(
                userSchema.getId(),
                userSchema.getFullName(),
                userSchema.getEmail(),
                userSchema.getPassword()
        );
    }

    @Override
    public User updateUserByUserId(String userId, User user) {
        UserSchema userSchema = this.userRepository.getReferenceById(userId);

        userSchema.setEmail(user.email());
        userSchema.setFullName(user.fullName());
        userSchema.setId(user.id());
        userSchema.setPassword(user.password());

        UserSchema updatedUser = this.userRepository.save(userSchema);

        return new User(
                updatedUser.getId(),
                updatedUser.getFullName(),
                updatedUser.getEmail(),
                updatedUser.getPassword()
        );
    }
}
