package com.faketri.market.infastructure.user.payload.auth.controller;

import com.faketri.market.infastructure.user.payload.auth.gateway.AuthService;
import com.faketri.market.infastructure.user.payload.auth.gateway.JwtService;
import com.faketri.market.infastructure.user.payload.user.dto.SignInRequest;
import com.faketri.market.usecase.user.payload.user.UserDetailsServerImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.net.http.HttpRequest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthPostController.class)
@AutoConfigureMockMvc
public class AuthPostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AuthService authService;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private UserDetailsServerImpl userDetailsServer;

    @BeforeEach
    public void setUp() {

    }

    @Test
    public void testSignIn() throws Exception {
        SignInRequest signInRequest = new SignInRequest("admin", "123123123");
        String signInRequestJson = objectMapper.writeValueAsString(signInRequest);

        MvcResult result = mockMvc.perform(post("/api/auth/sing-in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(signInRequestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }
}