package com.example.usermongodb.service;

import com.example.usermongodb.dto.UserDto;
import com.example.usermongodb.exception.ResourceNotFoundException;

/**
 * reducing the dependencies of a class that directly uses the different classes
 * userService interface
 */
public interface UserService {

    UserDto createUser(UserDto user);
    UserDto getUser(Long userId) throws ResourceNotFoundException;
}
