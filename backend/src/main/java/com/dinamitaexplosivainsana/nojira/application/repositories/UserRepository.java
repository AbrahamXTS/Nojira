package com.dinamitaexplosivainsana.nojira.application.repositories;

import com.dinamitaexplosivainsana.nojira.domain.models.User;

public interface UserRepository {
    User getUserByUserId(String userId);

    User getUserByEmail(String email);

    User saveUser(User user);

    User deleteUserByUserId(String userId);

    User updateUserByUserId(String userId, User user);
}
