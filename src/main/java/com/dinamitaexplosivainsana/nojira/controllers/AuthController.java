package com.dinamitaexplosivainsana.nojira.controllers;

import com.dinamitaexplosivainsana.nojira.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.dinamitaexplosivainsana.nojira.config.Constants.LOGIN_URL;

@Controller
@RequestMapping("auth")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("login")
    public String loginView() {
        return "login";
    }

    @GetMapping("signup")
    public String signupView() {
        return "signup";
    }

    @PostMapping("signup")
    public String signup(
            @RequestParam String fullName,
            @RequestParam String email,
            @RequestParam String password
    ) {
        try {
            authService.signup(fullName, email, password);
        } catch (Exception error) {
            // TODO: Implement handling of all service errors
        }

        return "redirect:" + LOGIN_URL;
    }
}
