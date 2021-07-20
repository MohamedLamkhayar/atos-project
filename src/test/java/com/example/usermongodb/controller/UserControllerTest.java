package com.example.usermongodb.controller;

import com.example.usermongodb.dao.UserRepository;
import com.example.usermongodb.mocks.MockeUser;
import com.example.usermongodb.rest.UserRestController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.mockito.Mockito.*;

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
    private UserRepository userRepositoryMock;

    // initialize error object
    @BeforeEach
    public void setUp() {
        erreur.put("country" , "user must be from france");
        erreur.put("age" , "user must have an age bigger than 18");
    }

    @Test
    public void shouldSaveUserTest() throws Exception {
        doReturn(MockeUser.getValidUser()).when(userRepositoryMock).save(any());

        ObjectMapper objectMapper = new ObjectMapper();

        this.mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(MockeUser.getValidUser())))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    public void shouldNotCreateInvalidDateAndCountry() throws Exception {
        doReturn(MockeUser.getInvalidUser()).when(userRepositoryMock).save(any());

        ObjectMapper objectMapper = new ObjectMapper();

        this.mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(MockeUser.getInvalidUser())))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().json(objectMapper.writeValueAsString(erreur)))
                .andReturn();
    }

    @Test
    public void shouldReturnUser() throws Exception {
        doReturn(Optional.of(MockeUser.getValidUser())).when(userRepositoryMock).findById(any());

        ObjectMapper objectMapper = new ObjectMapper();

        this.mockMvc.perform(get("/api/v1/users/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(MockeUser.getValidUser())))
                .andReturn();
    }
}
