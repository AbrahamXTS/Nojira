package com.dinamitaexplosivainsana.nojira.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("dashboard")
public class ProjectsController {

    @GetMapping("projects")
    public String allProjectsView() {
        return "projects";
    }
}
