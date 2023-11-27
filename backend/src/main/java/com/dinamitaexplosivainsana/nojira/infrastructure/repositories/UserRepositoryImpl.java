package com.dinamitaexplosivainsana.nojira.infrastructure.repositories;

import com.dinamitaexplosivainsana.nojira.application.repositories.UserRepository;
import com.dinamitaexplosivainsana.nojira.domain.models.User;
import com.dinamitaexplosivainsana.nojira.infrastructure.mappers.UserMapper;
import com.dinamitaexplosivainsana.nojira.infrastructure.schemas.UserSchema;
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

        return UserMapper.mapToModel(userSchema);
    }

    @Override
    public User getUserByEmail(String email) {
        UserSchema userSchema = this.userRepository.findByEmail(email);

        if (Objects.isNull(userSchema)) {
            return null;
        }

        return UserMapper.mapToModel(userSchema);
    }

    @Override
    public User saveUser(User user) {
        UserSchema userSchema = this.userRepository.save(
                UserMapper.mapToSchema(user));

        return UserMapper.mapToModel(userSchema);
    }
}
