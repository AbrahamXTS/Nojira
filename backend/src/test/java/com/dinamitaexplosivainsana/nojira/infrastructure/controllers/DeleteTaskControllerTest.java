package com.dinamitaexplosivainsana.nojira.infrastructure.controllers;

import com.dinamitaexplosivainsana.nojira.application.services.DeleteTaskService;
import com.dinamitaexplosivainsana.nojira.domain.dto.SuccessfulEliminationTaskDTO;
import com.dinamitaexplosivainsana.nojira.domain.dto.WrapperResponse;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc()
@TestPropertySource(locations = "classpath:application-test.properties")
public class DeleteTaskControllerTest {
    private final Gson gson = new Gson();
    @MockBean
    private DeleteTaskService deleteTaskService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @Tag("HappyPath")
    @WithMockUser
    void shouldDeleteATaskIfEveryIfOwnersAreCorrect() throws Exception{
        final String MOCK_TASK_ID = "someTaskId";
        final String MOCK_PROJECT_ID = "SomeProjectId";
        final String MOCK_USER_ID = "someUserId";


        doReturn(new SuccessfulEliminationTaskDTO(MOCK_TASK_ID))
                .when(deleteTaskService).delete(any(), any(), any());

        MvcResult result = this.mockMvc
                .perform(delete("/user/"+MOCK_USER_ID+"/projects/"+MOCK_PROJECT_ID+"/tasks/"+MOCK_TASK_ID)
                        .header("Content-Type", MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Type responseType = new TypeToken<WrapperResponse<SuccessfulEliminationTaskDTO>>(){
        }.getType();

        WrapperResponse<SuccessfulEliminationTaskDTO>response=
                gson.fromJson(result.getResponse().getContentAsString(), responseType);

        Assertions.assertTrue(response.ok());
        Assertions.assertInstanceOf(SuccessfulEliminationTaskDTO.class, response.body());
    }
}
