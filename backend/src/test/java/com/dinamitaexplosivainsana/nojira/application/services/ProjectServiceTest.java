package com.dinamitaexplosivainsana.nojira.application.services;

import com.dinamitaexplosivainsana.nojira.application.repositories.ProjectRepository;
import com.dinamitaexplosivainsana.nojira.application.repositories.RoleRepository;
import com.dinamitaexplosivainsana.nojira.application.repositories.UserRepository;
import com.dinamitaexplosivainsana.nojira.domain.dto.CreateProjectDTO;
import com.dinamitaexplosivainsana.nojira.domain.dto.CreatedProjectManagementDTO;
import com.dinamitaexplosivainsana.nojira.domain.dto.OwnerDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
class ProjectServiceTest {
    @Mock
    private ProjectRepository projectRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ProjectService projectService;
    private CreateProjectDTO projectDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        projectDTO = new CreateProjectDTO("Nojira", "Proyecto de ejemplo");
    }

    @Test
    void createProject() {
        when(projectService.createProject(projectDTO, "cf66f017-7d0a-4bd9-b07d-a9b06ca50319")).thenReturn(new CreatedProjectManagementDTO(
                anyString(),
                anyString(),
                anyString(),
                new OwnerDTO("cf66f017-7d0a-4bd9-b07d-a9b06ca50319", "Abraham Espinosa Mendoza")
        ));

        assertNotNull(projectService.createProject(projectDTO, anyString()));
    }
}