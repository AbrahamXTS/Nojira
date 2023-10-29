package com.dinamitaexplosivainsana.nojira.application.services;

import com.dinamitaexplosivainsana.nojira.application.repositories.UserRepository;
import com.dinamitaexplosivainsana.nojira.application.utils.JWTUtils;
import com.dinamitaexplosivainsana.nojira.domain.dto.SuccessfulAuthenticationDTO;
import com.dinamitaexplosivainsana.nojira.domain.dto.UserLoginDTO;
import com.dinamitaexplosivainsana.nojira.domain.dto.UserSignupDTO;
import com.dinamitaexplosivainsana.nojira.domain.exceptions.*;
import com.dinamitaexplosivainsana.nojira.domain.validators.UserSignupValidator;
import com.dinamitaexplosivainsana.nojira.infrastructure.schemas.UserSchema;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Objects;

import static com.dinamitaexplosivainsana.nojira.domain.config.Constants.AUTHENTICATION_FAILED_EXCEPTION_MESSAGE;
import static com.dinamitaexplosivainsana.nojira.domain.config.Constants.USER_ALREADY_EXIST_EXCEPTION_MESSAGE;

public class AuthService {
    private final JWTUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public AuthService(JWTUtils jwtUtils, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public SuccessfulAuthenticationDTO login(UserLoginDTO user) {
        UserSchema userSchema = userRepository.findByEmail(user.email())
                .orElseGet(() -> null);

        if (Objects.isNull(userSchema) || !(passwordEncoder.matches(user.password(), userSchema.getPassword()))) {
            throw new AuthenticationFailedException(AUTHENTICATION_FAILED_EXCEPTION_MESSAGE);
        }

        return new SuccessfulAuthenticationDTO(
                userSchema.getFullName(),
                userSchema.getEmail(),
                jwtUtils.generateAccessToken(user.email())
        );
    }

    public SuccessfulAuthenticationDTO signup(UserSignupDTO user) {
        UserSignupValidator.validate(user);

        if (userRepository.findByEmail(user.email()).isPresent()) {
            throw new UserAlreadyExistsException(USER_ALREADY_EXIST_EXCEPTION_MESSAGE);
        }

        UserSchema savedUser = this.userRepository.save(
                UserSchema.builder()
                        .fullName(user.fullName())
                        .email(user.email())
                        .password(passwordEncoder.encode(user.password()))
                        .build()
        );

        return new SuccessfulAuthenticationDTO(
                savedUser.getFullName(),
                savedUser.getEmail(),
                jwtUtils.generateAccessToken(user.email())
        );
    }
}
