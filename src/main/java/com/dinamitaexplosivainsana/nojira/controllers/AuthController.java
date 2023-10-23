package com.dinamitaexplosivainsana.nojira.controllers;

import com.dinamitaexplosivainsana.nojira.exceptions.EmptyDataException;
import com.dinamitaexplosivainsana.nojira.exceptions.IncorrectEmailFormatException;
import com.dinamitaexplosivainsana.nojira.exceptions.IncorrectFullNameFormatException;
import com.dinamitaexplosivainsana.nojira.exceptions.UserAlreadyExistsException;
import com.dinamitaexplosivainsana.nojira.services.AuthService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
            @RequestParam String password, RedirectAttributes redirect) {
        try {
            authService.signup(fullName, email, password);
            redirect.addFlashAttribute("successful", true); 
        } catch (EmptyDataException error) {
            redirect.addFlashAttribute("emptyFieldError", error.getMessage()); 
        } catch (IncorrectFullNameFormatException error) {
            redirect.addFlashAttribute("fullNameError", error.getMessage()); 
        } catch (IncorrectEmailFormatException error) {
            redirect.addFlashAttribute("emailError", error.getMessage()); 
        } catch (UserAlreadyExistsException error) {
            redirect.addFlashAttribute("emailError", error.getMessage());  
        } catch (Exception error) {} 
        finally{
            if (!redirect.getFlashAttributes().isEmpty()) {
                redirect.addFlashAttribute("fullName",fullName); 
                redirect.addFlashAttribute("email", email); 
            }
        }

        return "redirect:/auth/signup";
    }
}
