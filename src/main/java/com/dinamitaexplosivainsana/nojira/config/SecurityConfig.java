package com.dinamitaexplosivainsana.nojira.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static com.dinamitaexplosivainsana.nojira.config.Constants.LOGIN_URL;
import static com.dinamitaexplosivainsana.nojira.config.Constants.LOGOUT_URL;
import static com.dinamitaexplosivainsana.nojira.config.Constants.MAIN_PAGE_URL;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private final UserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider configureDaoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(getPasswordEncoder());

        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain configureSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authenticationProvider(configureDaoAuthenticationProvider())
                .authorizeHttpRequests(requests -> {
                    requests.requestMatchers("/auth/**", "/actuator/**", "/docs", "swagger-ui/**", "/v3/api-docs/**").permitAll();
                    requests.anyRequest().authenticated();
                })
                .formLogin(login -> {
                    login.loginPage(LOGIN_URL);
                    login.loginProcessingUrl(LOGIN_URL);
                    login.failureUrl(LOGIN_URL + "?error=true");
                    login.defaultSuccessUrl(MAIN_PAGE_URL);
                    login.permitAll();
                })
                .logout(logout -> {
                    logout.logoutUrl(LOGOUT_URL);
                    logout.deleteCookies("JSESSIONID");
                    logout.invalidateHttpSession(true);
                    logout.permitAll();
                })
                .sessionManagement(session -> {
                    session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
                    session.invalidSessionUrl(LOGIN_URL + "?closed=true");
                    session.maximumSessions(1);
                });

        return httpSecurity.build();
    }
}
