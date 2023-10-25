package com.dinamitaexplosivainsana.nojira.utils;

import com.dinamitaexplosivainsana.nojira.repositories.UserRepository;
import com.dinamitaexplosivainsana.nojira.schemas.UserSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthUtils {
    private final UserRepository userRepository;

    @Autowired
    private AuthUtils(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserSchema getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return userRepository.findByEmail(authentication.getName());
        }

        return null;
    }
}
