package com.dinamitaexplosivainsana.nojira.application.repositories;

import com.dinamitaexplosivainsana.nojira.infrastructure.schemas.UserSchema;

import java.util.Optional;

public interface UserRepository {
	Optional<UserSchema> findByEmail(String email);

	UserSchema save(UserSchema user);
}
