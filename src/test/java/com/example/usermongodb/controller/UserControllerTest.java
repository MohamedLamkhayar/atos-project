package com.example.usermongodb.controller;


import com.example.usermongodb.mocks.MockeUser;
import com.example.usermongodb.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;
import java.util.Map;


import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * test integration
 * simulate rest calls
 */
@AutoConfigureMockMvc
@SpringBootTest
public class UserControllerTest {

    // mock errors of validation
    private Map<String, String> erreur = new HashMap<>();

    // mock rest webservice calls
    @Autowired
    private MockMvc mockMvc;

    // inject mock of user repository
    @MockBean
    private UserService service;

    ObjectMapper objectMapper = new ObjectMapper();

    /**
     * initialise test context
     */
    @BeforeEach
    public void setUp() {
        erreur.put("country" , "user must be from france");
        erreur.put("age" , "user must have an age bigger than 18");
    }

    /**
     * test the rest service - getUserById
     * @throws Exception
     */
    @Test
    public void shouldReturnUser() throws Exception {
        doReturn(MockeUser.getValidUser()).when(service).getUser(any());

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/api/v1/users/1").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expected = objectMapper.writeValueAsString(MockeUser.getValidUser());

        JSONAssert.assertEquals(expected , result.getResponse()
                .getContentAsString(), false);
    }

    /**
     * test the rest service - createUser
     * case of using a valid user
     * @throws Exception
     */
    @Test
    public void shouldSaveUserTest() throws Exception {
        doReturn(MockeUser.getValidUser()).when(service).createUser(any());

        ObjectMapper objectMapper = new ObjectMapper();

        this.mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(MockeUser.getValidUser())))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();
    }

    /**
     * test the rest service - createUser
     * case of using an invalid user
     * age < 18 and the country is different to France
     * @throws Exception 400 invalid -age- & -country-
     */
    @Test
    public void shouldNotCreateInvalidDateAndCountry() throws Exception {
        doReturn(MockeUser.getInvalidUser()).when(service).createUser(any());

        ObjectMapper objectMapper = new ObjectMapper();

        this.mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(MockeUser.getInvalidUser())))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().json(objectMapper.writeValueAsString(erreur)))
                .andReturn();
    }

}
