package com.dinamitaexplosivainsana.nojira.controllers;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import com.dinamitaexplosivainsana.nojira.services.AuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    AuthController authController;

    @MockBean
    private AuthService authService;

    @Test
    void shouldInputForEmailBePresent() throws Exception {
        this.mockMvc
                .perform(get("/auth/login"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Correo electrónico")));
    }

    @Test
    void shouldBeRedirectedToLoginIfUserIsUnauthorized() throws Exception {
        this.mockMvc
                .perform(get("/dashboard/projects"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/auth/login"));
    }

    @Test
    void controllerShouldCallTheServiceToRegisterAnUserAndItsOk() throws Exception {
        doNothing().when(authService).signup(anyString(), anyString(), anyString());

        this.mockMvc
                .perform(post("/auth/signup"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/auth/login?closed=true"));
    }
}