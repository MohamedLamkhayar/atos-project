package com.example.usermongodb.dao;

import com.example.usermongodb.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
////Implementing the Repository pattern for User's entity with JPA & Hibernate
public interface UserRepository extends MongoRepository<User, Long> {
}
