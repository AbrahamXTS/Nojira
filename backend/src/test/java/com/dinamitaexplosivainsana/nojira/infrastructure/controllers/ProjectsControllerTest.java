package com.dinamitaexplosivainsana.nojira.infrastructure.controllers;

import com.dinamitaexplosivainsana.nojira.application.services.ProjectService;
import com.dinamitaexplosivainsana.nojira.domain.dto.CreateProjectDTO;
import com.dinamitaexplosivainsana.nojira.domain.dto.CreatedProjectManagementDTO;
import com.dinamitaexplosivainsana.nojira.domain.dto.OwnerDTO;
import com.dinamitaexplosivainsana.nojira.domain.dto.WrapperResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.lang.reflect.Type;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc()
@TestPropertySource(locations = "classpath:application-test.properties")
public class ProjectsControllerTest {
    private final Gson gson = new Gson();

    @MockBean
    private ProjectService projectService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Tag("HappyPath")
    @WithMockUser(username = "Ruben", authorities = {"USER"}, id = "UUID")
    void projectController_CreateProject_ReturnCreated() throws Exception {
        final String MOCK_PROJECT_ID = "UUID";
        final String MOCK_PROJECT_NAME = "Nojira";
        final String MOCK_DESCRIPTION = "Description";

        final String MOCK_OWNER_ID = "UUID";
        final String MOCK_OWNER_FULL_NAME = "Ruben";

        final OwnerDTO MOCK_OWNER = new OwnerDTO(MOCK_OWNER_ID, MOCK_OWNER_FULL_NAME);

        doReturn(new CreatedProjectManagementDTO(MOCK_PROJECT_ID, MOCK_PROJECT_NAME, MOCK_DESCRIPTION, MOCK_OWNER))
                .when(projectService).createProject(any(), any());

        String requestBody = gson.toJson(new CreateProjectDTO(MOCK_PROJECT_NAME, MOCK_DESCRIPTION));

        MvcResult result = this.mockMvc
                .perform(post("/user/" + MOCK_OWNER_ID + "/projects")
                        .header("Content-Type", MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(requestBody)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();

        Type responseType = new TypeToken<WrapperResponse<CreatedProjectManagementDTO>>() {
        }.getType();

        WrapperResponse<CreatedProjectManagementDTO> response =
                gson.fromJson(result.getResponse().getContentAsString(), responseType);

        Assertions.assertNotNull(result.getResponse().getContentAsString());
        Assertions.assertTrue(response.ok());
        Assertions.assertInstanceOf(CreatedProjectManagementDTO.class, response.body());
    }
}
