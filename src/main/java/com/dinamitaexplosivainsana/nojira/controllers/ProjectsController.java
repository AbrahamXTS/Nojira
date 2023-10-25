package com.dinamitaexplosivainsana.nojira.controllers;

import com.dinamitaexplosivainsana.nojira.schemas.UserSchema;
import com.dinamitaexplosivainsana.nojira.utils.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("dashboard")
public class ProjectsController {
    private final AuthUtils authUtils;

    @Autowired
    public ProjectsController(AuthUtils authUtils) {
        this.authUtils = authUtils;
    }

    @GetMapping("projects")
    public String allProjectsView(Model model) {
        UserSchema authenticatedUser = authUtils.getAuthenticatedUser();

        model.addAttribute("name", authenticatedUser.getFullName());

        return "projects";
    }
}
