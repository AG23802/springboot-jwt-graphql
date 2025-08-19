package com.example.springbootjwtgraphql;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.springbootjwtgraphql.api.rest.controllers.v1.XController;
import com.example.springbootjwtgraphql.application.security.JwtAuthenticationEntryPoint;
import com.example.springbootjwtgraphql.application.security.JwtUtil;
import com.example.springbootjwtgraphql.application.services.UserService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(XController.class)
@AutoConfigureMockMvc(addFilters = false)
public class XControllerTest {
        @Autowired
        private MockMvc mockMvc;

        @MockitoBean
        private JwtUtil jwtUtil;

        @MockitoBean
        private UserService userService;

        @MockitoBean
        private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

        @Test
        public void testSayHello_shouldReturnHelloWorld() throws Exception {
                mockMvc.perform(MockMvcRequestBuilders.get("/hello"))
                                .andExpect(status().isOk())
                                .andExpect(content().json("[4 , 16, 9]"));
        }
}