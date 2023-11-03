package com.dinamitaexplosivainsana.nojira.infrastructure.controllers;

import com.dinamitaexplosivainsana.nojira.application.services.ProjectService;
import com.dinamitaexplosivainsana.nojira.domain.dto.CreateProjectDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class ProjectsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProjectsController projectsController;

    @MockBean
    private ProjectService projectService;

    @Test
    void controllerShouldCallProjectService() throws Exception {
        doNothing().when(projectService).createProject(new CreateProjectDTO(anyString(), anyString()), anyString());

        this.mockMvc
                .perform(post("/user/{id}/projects/create"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }
}
