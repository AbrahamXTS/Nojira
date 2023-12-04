package com.dinamitaexplosivainsana.nojira.infrastructure.config;

import com.dinamitaexplosivainsana.nojira.infrastructure.security.JWTAuthorizationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
/**
 * Configuration class for setting up Spring Security in the application.
 * It defines authentication and authorization rules, as well as security filters.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private final JWTAuthorizationFilter jwtAuthorizationFilter;
    private final UserDetailsService userDetailsService;
     /**
     * Constructs a {@code SecurityConfig} instance.
     *
     * @param jwtAuthorizationFilter The JWT authorization filter for handling JWT authentication.
     * @param userDetailsService      The service providing user-related information.
     */
    public SecurityConfig(
            JWTAuthorizationFilter jwtAuthorizationFilter,
            UserDetailsService userDetailsService
    ) {
        this.jwtAuthorizationFilter = jwtAuthorizationFilter;
        this.userDetailsService = userDetailsService;
    }
    /**
     * Provides a bean for the {@code PasswordEncoder} interface using BCrypt hashing.
     *
     * @return The BCryptPasswordEncoder bean.
     */
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    /**
     * Configures and provides a bean for {@code DaoAuthenticationProvider}.
     *
     * @return The configured DaoAuthenticationProvider bean.
     */
    @Bean
    public DaoAuthenticationProvider configureDaoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(getPasswordEncoder());

        return daoAuthenticationProvider;
    }
    /**
     * Configures the main security settings for the application.
     *
     * @param httpSecurity The HttpSecurity object to configure.
     * @return The configured SecurityFilterChain bean.
     * @throws Exception If an error occurs during configuration.
     */
    @Bean
    public SecurityFilterChain configureSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .authenticationProvider(configureDaoAuthenticationProvider())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/auth/**", "/actuator/**", "/docs", "swagger-ui/**", "/v3/api-docs/**").permitAll();
					auth.requestMatchers(request -> HttpMethod.OPTIONS.matches(request.getMethod())).permitAll();
                    auth.anyRequest().authenticated();
                })
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return httpSecurity.build();
    }
}
