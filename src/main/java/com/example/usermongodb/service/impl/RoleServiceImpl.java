package com.example.usermongodb.service.impl;

import com.example.usermongodb.config.sequence.SequenceService;
import com.example.usermongodb.dao.RoleRepository;
import com.example.usermongodb.exception.ResourceNotFoundException;
import com.example.usermongodb.models.Role;
import com.example.usermongodb.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private SequenceService sequenceService;

    @Autowired
    private RoleRepository roleRepository;

    /**
     * redefine the method of implemented RoleService
     * should get a role by a name
     * @param name
     * @return
     * @throws ResourceNotFoundException
     */
    @Override
    public Role getRoleByName(String name) throws ResourceNotFoundException {
        Role role = roleRepository.findByName(name).orElseThrow(() -> new ResourceNotFoundException("Role not found for this name :: " + name));
        return role;
    }


}
