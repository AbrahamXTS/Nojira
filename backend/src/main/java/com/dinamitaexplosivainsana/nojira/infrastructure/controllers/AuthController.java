package com.dinamitaexplosivainsana.nojira.infrastructure.controllers;

import com.dinamitaexplosivainsana.nojira.application.services.AuthService;
import com.dinamitaexplosivainsana.nojira.domain.dto.SuccessfulAuthenticationDTO;
import com.dinamitaexplosivainsana.nojira.domain.dto.UserLoginDTO;
import com.dinamitaexplosivainsana.nojira.domain.dto.UserSignupDTO;
import com.dinamitaexplosivainsana.nojira.domain.dto.WrapperResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@CrossOrigin(maxAge = 3600, methods = {RequestMethod.OPTIONS, RequestMethod.POST}, origins = {"*"})
@Tag(name = "Authentication", description = "The authentication handler contains the login and registration of new users to generate a valid JWT token that allows signing the rest of the requests.")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("login")
    @Operation(summary = "Login", description = "Endpoint to login an existing user.")
    public ResponseEntity<WrapperResponse<SuccessfulAuthenticationDTO>> login(
            @RequestBody UserLoginDTO user
    ) {
        SuccessfulAuthenticationDTO response = authService.login(user);

        return new ResponseEntity<>(
                new WrapperResponse<>(
                        true,
                        "Bienvenido de vuelta " + response.fullName(),
                        response
                ),
                HttpStatus.OK
        );
    }

    @PostMapping("signup")
    @Operation(summary = "Signup", description = "Endpoint to register a new user.")
    public ResponseEntity<WrapperResponse<SuccessfulAuthenticationDTO>> signup(
            @RequestBody UserSignupDTO user
    ) {
        SuccessfulAuthenticationDTO response = authService.signup(user);

        return new ResponseEntity<>(
                new WrapperResponse<>(
                        true,
                        "Usuario " + user.email() + " registrado correctamente",
                        response
                ),
                HttpStatus.CREATED
        );
    }
}
