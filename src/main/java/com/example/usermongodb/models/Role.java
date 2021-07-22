package com.example.usermongodb.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "roles")
@Data
public class Role implements Serializable {

    /**
     * not created in database
     * it's help to get the user sequence to increment the id
     */
    @Transient
    public static final String SEQUENCE_NAME = "roles_sequence";

    /** id of the role */
    @Id
    private Long id;
    /** name of the role */
    private String name;
}
