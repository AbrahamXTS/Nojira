package com.dinamitaexplosivainsana.nojira.infrastructure.security;

import com.dinamitaexplosivainsana.nojira.application.repositories.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.dinamitaexplosivainsana.nojira.domain.models.User userSchema = userRepository.getUserByEmail(username);

        if (Objects.isNull(userSchema)) {
            throw new UsernameNotFoundException("Username not found!");
        }

        return new User(
                userSchema.email(),
                userSchema.password(),
                true,
                true,
                true,
                true,
                Collections.singletonList(new SimpleGrantedAuthority("GENERIC"))
        );
    }
}