package com.dinamitaexplosivainsana.nojira.services;

import com.dinamitaexplosivainsana.nojira.exceptions.UserAlreadyExistsException;
import com.dinamitaexplosivainsana.nojira.exceptions.EmptyDataException;
import com.dinamitaexplosivainsana.nojira.exceptions.IncorrectEmailFormatException;
import com.dinamitaexplosivainsana.nojira.exceptions.IncorrectFullNameFormatException;
import com.dinamitaexplosivainsana.nojira.repositories.UserRepository;
import com.dinamitaexplosivainsana.nojira.schemas.UserSchema;
import com.dinamitaexplosivainsana.nojira.validators.UserSchemaValidator;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public AuthService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public void signup(final String fullName, final String email, final String password) throws UserAlreadyExistsException, EmptyDataException, IncorrectEmailFormatException, IncorrectFullNameFormatException {

        UserSchemaValidator.validate(fullName, email, password);

        UserSchema user = userRepository.findByEmail(email);

        if (Objects.nonNull(user)) {
            throw new UserAlreadyExistsException("Ya existe una cuenta asociada a este correo electrónico");
        }

        UserSchema newUser = UserSchema.builder()
                .fullName(fullName)
                .email(email)
                .password(passwordEncoder.encode(password))
                .build();

        this.userRepository.save(newUser);
    }
}
