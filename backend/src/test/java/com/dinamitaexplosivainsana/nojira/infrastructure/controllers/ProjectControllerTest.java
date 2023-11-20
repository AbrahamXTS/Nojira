package com.dinamitaexplosivainsana.nojira.infrastructure.controllers;

import com.dinamitaexplosivainsana.nojira.application.repositories.UserRepository;
import com.dinamitaexplosivainsana.nojira.application.services.ProjectService;
import com.dinamitaexplosivainsana.nojira.domain.dto.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
class ProjectControllerTest {
    private final Gson gson = new Gson();

    @MockBean
    private ProjectService projectService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    @Tag("HappyPath")
    @WithMockUser
    void shouldGetAllProjectsIfUserDataIsOk() throws Exception {
        OwnerDTO owner = new OwnerDTO("ownerUserId", "ownerUserName");

        doReturn(Collections.singletonList(new ProjectDTO("projectId", "ProjectName", "ProjectDescription", owner)))
                .when(projectService).getAllProjectsByUserId(any());

        MvcResult result = this.mockMvc.perform(get("/projects/userId")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
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
        StatusDTO expectedStatus = new StatusDTO("Por hacer");
        TimesDTO expectedTimes = new TimesDTO(1000, 5);
        AssignedDTO expectedAssigned = new AssignedDTO("userId", "userName");
        TaskDTO expectedTask = new TaskDTO("taskId", "TaskTitle", "TaskDescription", expectedStatus, expectedTimes, expectedAssigned);

        doReturn(Collections.singletonList(new ProjectInfoDTO("validProjectId", "ProjectName", Collections.singletonList(expectedTask))))
                .when(projectService).getAllTasksPerProject(any());

        MvcResult result = mockMvc.perform(get("/projects/validProjectId/tasks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();

        Type responseType = new TypeToken<WrapperResponse<List<ProjectInfoDTO>>>(){}.getType();

        WrapperResponse<List<ProjectInfoDTO>> listResponse =
                gson.fromJson(result.getResponse().getContentAsString(), responseType);

        Assertions.assertTrue(listResponse.ok());
    }

    @Test
    @Tag("UnhappyPath")
    @WithMockUser
    void shouldGetErrorIfUserDoesNotExist() throws Exception {
        String UUID = "9af65d84-f091-4012-8530-fe6aeecb8ac3";

        when(projectService.getAllProjectsByUserId(anyString()))
                .thenCallRealMethod();

        this.mockMvc.perform(get("/projects/" + UUID)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound());
    }

    @Test
    @Tag("UnhappyPath")
    @WithMockUser
    void shouldGetErrorIfProjectDoesNotExist(){

    }

    @Test
    @Tag("UnhappyPath")
    @WithMockUser
    void shouldGetErrorIfProjectIdIsIncorrect() throws Exception {
        doCallRealMethod().when(projectService).getAllProjectsByUserId(any());

        this.mockMvc.perform(get("/projects/userId")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @Tag("UnhappyPath")
    @WithMockUser
    void shouldGetErrorIfUserIdIsIncorrect() throws Exception {
        doCallRealMethod().when(projectService).getAllProjectsByUserId(any());

        this.mockMvc.perform(get("/projects/userId")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest());

    }















}
