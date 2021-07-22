package com.example.usermongodb.service;

import com.example.usermongodb.exception.ResourceNotFoundException;
import com.example.usermongodb.models.Role;

/**
 * reducing the dependencies of a class that directly uses the different classes
 * roleService interface
 */
public interface RoleService {

    Role getRoleByName(String name) throws ResourceNotFoundException;
}
