package com.dinamitaexplosivainsana.nojira.services;

import com.dinamitaexplosivainsana.nojira.repositories.UserRepository;
import com.dinamitaexplosivainsana.nojira.schemas.UserSchema;
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

    public void signup(final String fullName, final String email, final String password) {
        UserSchema user = UserSchema.builder()
                .fullName(fullName)
                .email(email)
                .password(passwordEncoder.encode(password))
                .build();

        this.userRepository.save(user);
    }
}
