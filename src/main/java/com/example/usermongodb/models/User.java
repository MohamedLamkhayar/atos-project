package com.example.usermongodb.models;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Set;

@Document(collection = "users")
@Data
public class User implements Serializable {

    /**
     * not created in database
     * it's help to get the user sequence to increment the id
     */
    @Transient
    public static final String SEQUENCE_NAME = "user_sequence";

    /** id of the user */
    @Id
    private Long id ;
    /** lastName of the user */
    private String lastName;
    /** firstName of the user */
    private String firstName;
    /** age of the user */
    private Integer age;
    /** country of the user */
    private String country;
    /** password of the user */
    private String password;
    /** detect if the user is active */
    private boolean enabled;
    /** list of roles of the user */
    @DBRef
    private Set<Role> roles;

}
