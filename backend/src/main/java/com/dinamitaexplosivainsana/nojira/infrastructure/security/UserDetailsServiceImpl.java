package com.dinamitaexplosivainsana.nojira.infrastructure.security;

import com.dinamitaexplosivainsana.nojira.application.repositories.UserRepository;
import com.dinamitaexplosivainsana.nojira.infrastructure.schemas.UserSchema;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	private final UserRepository userRepository;

	public UserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserSchema userSchema = userRepository.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("Username not found!"));

		return new User(
				userSchema.getEmail(),
				userSchema.getPassword(),
				true,
				true,
				true,
				true,
				Collections.singletonList(new SimpleGrantedAuthority("GENERIC"))
		);
	}
}