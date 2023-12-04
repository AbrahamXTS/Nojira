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
/**
 * Controller class handling authentication-related operations such as login and user registration.
 * This controller is responsible for generating valid JWT tokens for subsequent requests.
 *
 * @RestController Indicates that this class is a Spring MVC controller.
 * @RequestMapping("auth") Defines the base path for the controller.
 * @CrossOrigin Enables cross-origin resource sharing (CORS) for specific methods and origins.
 * @Tag(name = "Authentication", description = "The authentication handler contains the login and registration of new users to generate a valid JWT token that allows signing the rest of the requests.")
 * Swagger annotation providing metadata about the API.
 */
@RestController
@RequestMapping("auth")
@CrossOrigin(maxAge = 3600, methods = {RequestMethod.OPTIONS, RequestMethod.POST}, origins = {"*"})
@Tag(name = "Authentication", description = "The authentication handler contains the login and registration of new users to generate a valid JWT token that allows signing the rest of the requests.")
public class AuthController {
    private final AuthService authService;
     /**
     * Constructs an {@code AuthController} instance.
     *
     * @param authService The service responsible for authentication-related operations.
     */
    public AuthController(AuthService authService) {
        this.authService = authService;
    }
     /**
     * Handles the login endpoint to authenticate an existing user.
     *
     * @param user The {@code UserLoginDTO} containing user login information.
     * @return ResponseEntity containing a wrapped response with the result of the login operation.
     */
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
    /**
     * Handles the signup endpoint to register a new user.
     *
     * @param user The {@code UserSignupDTO} containing user registration information.
     * @return ResponseEntity containing a wrapped response with the result of the signup operation.
     */
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
