package com.dinamitaexplosivainsana.nojira.application.services;

import com.dinamitaexplosivainsana.nojira.application.repositories.UserRepository;
import com.dinamitaexplosivainsana.nojira.application.utils.JWTUtils;
import com.dinamitaexplosivainsana.nojira.domain.dto.SuccessfulAuthenticationDTO;
import com.dinamitaexplosivainsana.nojira.domain.dto.UserLoginDTO;
import com.dinamitaexplosivainsana.nojira.domain.dto.UserSignupDTO;
import com.dinamitaexplosivainsana.nojira.domain.exceptions.UnauthorizedAccessException;
import com.dinamitaexplosivainsana.nojira.domain.exceptions.ConflictWithExistingResourceException;
import com.dinamitaexplosivainsana.nojira.domain.models.User;
import com.dinamitaexplosivainsana.nojira.domain.validators.UserSignupValidator;
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
        User findedUser = userRepository.getUserByEmail(user.email());

        if (Objects.isNull(findedUser) || !(passwordEncoder.matches(user.password(), findedUser.password()))) {
            throw new UnauthorizedAccessException(AUTHENTICATION_FAILED_EXCEPTION_MESSAGE);
        }

        return new SuccessfulAuthenticationDTO(
                findedUser.id(),
                findedUser.fullName(),
                findedUser.email(),
                jwtUtils.generateAccessToken(user.email())
        );
    }

    public SuccessfulAuthenticationDTO signup(UserSignupDTO user) {
        UserSignupValidator.validate(user);

        if (Objects.nonNull(userRepository.getUserByEmail(user.email()))) {
            throw new ConflictWithExistingResourceException(USER_ALREADY_EXIST_EXCEPTION_MESSAGE);
        }

        User savedUser = this.userRepository.saveUser(
                new User(
                        null,
                        user.fullName(),
                        user.email(),
                        passwordEncoder.encode(user.password())
                )
        );

        return new SuccessfulAuthenticationDTO(
                savedUser.id(),
                savedUser.fullName(),
                savedUser.email(),
                jwtUtils.generateAccessToken(user.email())
        );
    }
}
