package com.example.usermongodb.rest;


import com.example.usermongodb.dto.RoleDto;
import com.example.usermongodb.dto.UserDto;
import com.example.usermongodb.exception.ResourceNotFoundException;
import com.example.usermongodb.models.Role;
import com.example.usermongodb.service.RoleService;
import com.example.usermongodb.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/users")
public class UserRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    /**
     * should get the user's details
     * @param userId
     * @return
     * @throws ResourceNotFoundException
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
        UserDto user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }

    /**
     * should create user
     * @param userDto
     * @param roleName not mandatory and have a default value
     * @return
     */
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto, @RequestParam(defaultValue = "User") String roleName) throws ResourceNotFoundException {
        Role role = roleService.getRoleByName(roleName);
        ModelMapper modelMapper = new ModelMapper();
        RoleDto roleDto = modelMapper.map(role, RoleDto.class);

        Set<RoleDto> roles = new HashSet<>();
        roles.add(roleDto);
        userDto.setRoles(roles);
        UserDto result = userService.createUser(userDto);

        return new ResponseEntity<UserDto>(result, HttpStatus.CREATED);
    }
}
