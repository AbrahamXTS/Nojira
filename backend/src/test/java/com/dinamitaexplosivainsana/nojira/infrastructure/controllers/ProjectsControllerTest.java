package com.dinamitaexplosivainsana.nojira.infrastructure.controllers;

import com.dinamitaexplosivainsana.nojira.application.services.ProjectService;
import com.dinamitaexplosivainsana.nojira.domain.dto.CreateProjectDTO;
import com.dinamitaexplosivainsana.nojira.domain.dto.CreatedProjectManagementDTO;
import com.dinamitaexplosivainsana.nojira.domain.dto.OwnerDTO;
import com.dinamitaexplosivainsana.nojira.domain.dto.WrapperResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.lang.reflect.Type;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@TestPropertySource(locations = "classpath:application-test.properties")
public class ProjectsControllerTest {
    private final Gson gson = new Gson();

    @MockBean
    ProjectsController projectsController;

    @MockBean
    private ProjectService projectService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Tag("CreatedCorrectly")
    void ProjectController_CreateProject_ReturnCreated() throws Exception {
        final String MOCK_PROJECT_ID = "UUID";
        final String MOCK_PROJECT_NAME = "Nojira";
        final String MOCK_DESCRIPTION = "Description";
        final String MOCK_OWNER_ID = "UUID";
        final String MOCK_OWNER_FULLNAME = "Ruben";
        final OwnerDTO MOCK_OWNER = new OwnerDTO(MOCK_OWNER_ID, MOCK_OWNER_FULLNAME);

        doReturn(new CreatedProjectManagementDTO(MOCK_PROJECT_ID, MOCK_PROJECT_NAME, MOCK_DESCRIPTION, MOCK_OWNER))
                .when(projectService).createProject(any(), any());

        String requestBody = gson.toJson(new CreateProjectDTO(MOCK_PROJECT_NAME, MOCK_DESCRIPTION));
        String pathVariable = MOCK_OWNER_ID;

        MvcResult result = this.mockMvc
                .perform(MockMvcRequestBuilders.post("/user/{id}/projects", pathVariable)
                        .header("Content-Type", MediaType.APPLICATION_JSON)
                        .content(requestBody)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();

        Type responseType = new TypeToken<WrapperResponse<CreatedProjectManagementDTO>>() {
        }.getType();

        WrapperResponse<CreatedProjectManagementDTO> response =
                gson.fromJson(result.getResponse().getContentAsString(), responseType);

        Assertions.assertTrue(response.ok());
        Assertions.assertInstanceOf(CreatedProjectManagementDTO.class, response.body());
        Assertions.assertNotNull(result.getResponse().getContentAsString());
    }
}
