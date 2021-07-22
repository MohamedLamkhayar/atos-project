package com.example.usermongodb.service;

import com.example.usermongodb.dao.RoleRepository;
import com.example.usermongodb.exception.ResourceNotFoundException;
import com.example.usermongodb.mocks.MockeUser;
import com.example.usermongodb.models.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.Mockito.doReturn;

@SpringBootTest
public class RoleServiceTest {

    @Autowired
    private RoleService service;

    // inject mock of repository
    @MockBean
    private RoleRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * testing get role using a his name (enum)
     * @throws ResourceNotFoundException user not found
     */
    @Test
    @DisplayName("Test getRoleByName Success")
    public void shoulGetRole() throws ResourceNotFoundException {
        Role role = modelMapper.map(MockeUser.getValidUser().getRoles().stream().findFirst().get(), Role.class);
        doReturn(Optional.of(role)).when(repository).findByName("User");
        Role result = service.getRoleByName("User");
        Assertions.assertEquals(role , result);
    }
}
