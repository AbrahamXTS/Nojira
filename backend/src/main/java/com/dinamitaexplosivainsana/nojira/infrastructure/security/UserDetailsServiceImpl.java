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
/**
 * Implementation of the Spring Security UserDetailsService interface.
 * This service is responsible for loading user details during authentication.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    /**
     * Constructor to initialize the UserDetailsServiceImpl with a UserRepository.
     *
     * @param userRepository The repository for accessing user data.
     */
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    /**
     * Loads user details by username during authentication.
     *
     * @param username The username for which user details should be loaded.
     * @return UserDetails representing the loaded user.
     * @throws UsernameNotFoundException If the username is not found in the repository.
     */
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