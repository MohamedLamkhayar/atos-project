package com.example.usermongodb.dao;

import com.example.usermongodb.models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
//Implementing the Repository pattern for Role's entity with JPA & Hibernate
public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(String name);
}
