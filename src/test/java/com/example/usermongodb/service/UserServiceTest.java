package com.example.usermongodb.service;

import com.example.usermongodb.dao.UserRepository;
import com.example.usermongodb.dto.UserDto;
import com.example.usermongodb.exception.ResourceNotFoundException;
import com.example.usermongodb.mocks.MockeUser;
import com.example.usermongodb.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
public class UserServiceTest {

    // mocking user repository
    @MockBean
    private UserRepository repository;

    @Autowired
    private UserService service;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * testing getUser
     * @throws ResourceNotFoundException
     */
    @Test
    @DisplayName("Test getUser Success")
    public void shouldFindUserById() throws ResourceNotFoundException {
        doReturn(Optional.of(modelMapper.map(MockeUser.getValidUser(), User.class))).when(repository).findById(1l);
        UserDto result = service.getUser(1l);
        Assertions.assertEquals(MockeUser.getValidUser() , result);
    }

    /**
     * testing createUser
     * @throws ResourceNotFoundException
     */
    @Test
    @DisplayName("Test createUser Success")
    public void shouldSaveUser() throws ResourceNotFoundException {
        doReturn(modelMapper.map(MockeUser.getValidUser(), User.class)).when(repository).save(any());
        UserDto result = service.createUser(MockeUser.getValidUser());
        Assertions.assertEquals(MockeUser.getValidUser() , result);
    }

}
