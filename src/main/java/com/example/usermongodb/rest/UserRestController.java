package com.example.usermongodb.rest;

import com.example.usermongodb.config.sequence.SequenceService;
import com.example.usermongodb.dao.RoleRepository;
import com.example.usermongodb.dao.UserRepository;
import com.example.usermongodb.exception.ResourceNotFoundException;
import com.example.usermongodb.models.Role;
import com.example.usermongodb.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/users")
public class UserRestController {

    @Autowired
    private SequenceService sequenceService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    /**
     * should get the user's details
     * @param userId
     * @return
     * @throws ResourceNotFoundException
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));

        return ResponseEntity.ok(user);
    }

    /**
     * should create user
     * @param user
     * @param roleName not mandatory and have a default value
     * @return
     */
    @PostMapping
    public ResponseEntity createUser(@Valid @RequestBody User user, @RequestParam(defaultValue = "USER") String roleName) {
        Role role = roleRepository.findByName(roleName);
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        user.setId(sequenceService.generateSequence(User.SEQUENCE_NAME));
        User result = userRepository.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
