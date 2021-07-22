package com.example.usermongodb.service.impl;

import com.example.usermongodb.config.sequence.SequenceService;
import com.example.usermongodb.dao.UserRepository;
import com.example.usermongodb.dto.UserDto;
import com.example.usermongodb.exception.ResourceNotFoundException;
import com.example.usermongodb.models.User;
import com.example.usermongodb.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private SequenceService sequenceService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * redefine the method of implemented UserService
     * should create a user
     * @param userDto
     * @return
     */
    @Override
    public UserDto createUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        user.setId(sequenceService.generateSequence(User.SEQUENCE_NAME));
        User result = userRepository.save(user);
        return modelMapper.map(result, UserDto.class);
    }

    /**
     * redefine the method of implemented UserService
     * should get a user by its id
     * @param userId
     * @return
     * @throws ResourceNotFoundException
     */
    @Override
    public UserDto getUser(Long userId) throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
        return modelMapper.map(user, UserDto.class);
    }
}
