package com.dinamitaexplosivainsana.nojira.services;

import com.dinamitaexplosivainsana.nojira.repositories.UserRepository;
import com.dinamitaexplosivainsana.nojira.schemas.UserSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserSchema user = userRepository.findByEmail(username);

        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("Oh no! Usuario no encontrado.");
        }

        return new User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }
}
