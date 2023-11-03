package com.dinamitaexplosivainsana.nojira.infrastructure.controllers;

import com.dinamitaexplosivainsana.nojira.application.services.AuthService;
import com.dinamitaexplosivainsana.nojira.domain.dto.SuccessfulAuthenticationDTO;
import com.dinamitaexplosivainsana.nojira.domain.dto.UserLoginDTO;
import com.dinamitaexplosivainsana.nojira.domain.dto.UserSignupDTO;
import com.dinamitaexplosivainsana.nojira.domain.dto.WrapperResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@CrossOrigin(maxAge = 3600, methods = {RequestMethod.OPTIONS, RequestMethod.POST}, origins = {"*"})
@Tag(name = "Authentication", description = "The authentication handler contains the login and registration of new users to generate a valid JWT token that allows signing the rest of the requests.")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("login")
    public ResponseEntity<WrapperResponse<SuccessfulAuthenticationDTO>> login(@RequestBody UserLoginDTO user) {
        return new ResponseEntity<>(new WrapperResponse<>(
                true,
                "Bienvenido de vuelta " + user.email(),
                authService.login(user)
        ),
                HttpStatus.OK
        );
    }

    @PostMapping("signup")
    public ResponseEntity<WrapperResponse<SuccessfulAuthenticationDTO>> signup(@RequestBody UserSignupDTO user) {
        return new ResponseEntity<>(new WrapperResponse<>(
                true,
                "Usuario " + user.email() + " registrado correctamente",
                authService.signup(user)
        ),
                HttpStatus.CREATED
        );
    }
}
