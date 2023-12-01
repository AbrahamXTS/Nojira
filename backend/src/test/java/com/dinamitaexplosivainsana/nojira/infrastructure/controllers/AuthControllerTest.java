package com.dinamitaexplosivainsana.nojira.infrastructure.controllers;

import com.dinamitaexplosivainsana.nojira.application.services.AuthService;
import com.dinamitaexplosivainsana.nojira.domain.dto.SuccessfulAuthenticationDTO;
import com.dinamitaexplosivainsana.nojira.domain.dto.UserSignupDTO;
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
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.lang.reflect.Type;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
class AuthControllerTest {
    private static final String SIGNUP_ENDPOINT = "/auth/signup";
    private final Gson gson = new Gson();
    @MockBean
    private AuthService authService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @Tag("HappyPath")
    void shouldRegisterANewUserIfEveryDataIsOk() throws Exception {
        final String MOCK_EMAIL = "John.doe@nojira.com";
        final String MOCK_FULL_NAME = "John Doe";
        final String MOCK_PASSWORD = "PASSWORD";
        final String MOCK_TOKEN = "TOKEN";
        final String MOCK_USER_ID = "UUID";

        doReturn(new SuccessfulAuthenticationDTO(MOCK_USER_ID, MOCK_FULL_NAME, MOCK_EMAIL, MOCK_TOKEN))
                .when(authService).signup(any());

        String requestBody = gson.toJson(new UserSignupDTO(MOCK_FULL_NAME, MOCK_EMAIL, MOCK_PASSWORD));

        MvcResult result = this.mockMvc
                .perform(post(SIGNUP_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();

        Type responseType = new TypeToken<WrapperResponse<SuccessfulAuthenticationDTO>>() {
        }.getType();

        WrapperResponse<SuccessfulAuthenticationDTO> response =
                gson.fromJson(result.getResponse().getContentAsString(), responseType);

        Assertions.assertTrue(response.ok());
        Assertions.assertInstanceOf(SuccessfulAuthenticationDTO.class, response.body());
    }

    @Test
    @Tag("UnhappyPath")
    void shouldThrowRequiredArgumentExceptionIfAnyParamIsEmpty() throws Exception {
        doCallRealMethod().when(authService).signup(any());

        String requestBody = gson
                .toJson(new UserSignupDTO("", "", ""));

        this.mockMvc
                .perform(post(SIGNUP_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                )
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @Tag("UnhappyPath")
    void shouldThrowFullNameFormatExceptionIfNameParamContainsANumber() throws Exception {
        doCallRealMethod().when(authService).signup(any());

        String requestBody = gson
                .toJson(new UserSignupDTO("John 1", "John.doe@nojira.com", "Password"));

        this.mockMvc
                .perform(post(SIGNUP_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                )
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @Tag("UnhappyPath")
    void shouldThrowEmailFormatExceptionIfEmailIsInvalid() throws Exception {
        doCallRealMethod().when(authService).signup(any());

        String requestBody = gson
                .toJson(new UserSignupDTO("John Doe", "john.com", "Password"));

        this.mockMvc
                .perform(post(SIGNUP_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                )
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}
