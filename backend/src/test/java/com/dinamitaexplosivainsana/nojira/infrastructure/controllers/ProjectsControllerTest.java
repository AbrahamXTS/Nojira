package com.dinamitaexplosivainsana.nojira.infrastructure.controllers;

import com.dinamitaexplosivainsana.nojira.application.services.ProjectService;
import com.dinamitaexplosivainsana.nojira.application.services.TaskService;
import com.dinamitaexplosivainsana.nojira.domain.dto.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
class ProjectsControllerTest {
    private static final String PROJECTS_ENDPOINT = "/user/FAKE_USER_ID/projects";
    private final Gson gson = new Gson();
    @MockBean
    private ProjectService projectService;
    @MockBean
    private TaskService taskService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @Tag("HappyPath")
    @WithMockUser
    void shouldGetAllProjectsIfUserDataIsOk() throws Exception {
        OwnerDTO owner = new OwnerDTO("ownerUserId", "ownerUserName");

        doReturn(Collections.singletonList(new ProjectDTO("projectId", "ProjectName", "ProjectDescription", owner)))
                .when(projectService).getAllProjectsByUserId(any());

        MvcResult result = this.mockMvc.perform(get(PROJECTS_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Type responseType = new TypeToken<WrapperResponse<List<ProjectDTO>>>() {
        }.getType();

        WrapperResponse<List<ProjectDTO>> listResponse =
                gson.fromJson(result.getResponse().getContentAsString(), responseType);

        Assertions.assertTrue(listResponse.ok());
        Assertions.assertFalse(listResponse.body().isEmpty());
    }

    @Test
    @Tag("HappyPath")
    @WithMockUser
    void shouldGetAllTasksIfProjectDataIsOk() throws Exception {
        TaskDTO taskDTO = new TaskDTO(
                "taskId",
                "taskTitle",
                "taskDescription",
                "taskStatus",
                new TimesDTO(0, 0),
                new OwnerDTO("taskOwnerId", "taskOwnerFullName")
        );

        doReturn(new ProjectWithTasksDTO("validProjectId", "ProjectName", Collections.singletonList(taskDTO)))
                .when(taskService).getAllTasksByProjectId(any());

        MvcResult result = mockMvc.perform(get(PROJECTS_ENDPOINT + "/FAKE_PROJECT_ID/tasks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Type responseType = new TypeToken<WrapperResponse<ProjectWithTasksDTO>>() {
        }.getType();

        WrapperResponse<ProjectWithTasksDTO> response =
                gson.fromJson(result.getResponse().getContentAsString(), responseType);

        Assertions.assertTrue(response.ok());
    }

    @Test
    @Tag("HappyPath")
    @WithMockUser
    void shouldCreateANewProjectIfEveryDataIsOk() throws Exception {
        ProjectDTO projectDTO = new ProjectDTO(
                "projectId",
                "title",
                "projectDescription",
                new OwnerDTO("projectOwnerId", "projectOwnerFullName")
        );

        doReturn(projectDTO)
                .when(projectService).create(any(), any());

        String requestBody = gson.toJson(new CreateProjectDTO(anyString(), anyString()));

        MvcResult result = this.mockMvc
                .perform(post(PROJECTS_ENDPOINT)
                        .header("Content-Type", MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(requestBody)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();

        Assertions.assertNotNull(result.getResponse().getContentAsString());

        Type responseType = new TypeToken<WrapperResponse<ProjectDTO>>() {
        }.getType();

        WrapperResponse<ProjectDTO> response =
                gson.fromJson(result.getResponse().getContentAsString(), responseType);

        Assertions.assertTrue(response.ok());
        Assertions.assertInstanceOf(ProjectDTO.class, response.body());
    }
}
