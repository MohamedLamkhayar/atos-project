package com.example.usermongodb.models;

import com.example.usermongodb.enums.CountryEnum;
import com.example.usermongodb.validation.ValueOfEnumCountry;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Document(collection = "users")
@Data
public class User {

    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";

    @Id
    private Long id ;
    @NotBlank(message = "lastName is mandatory")
    @Size(max = 100, message = "only 100 is allowed")
    private String lastName;
    @NotBlank(message = "firstName is mandatory")
    @Size(max = 100, message = "only 100 is allowed")
    private String firstName;
    @Min(value = 18 , message = "user must have an age bigger than 18")
    private Integer age;
    @ValueOfEnumCountry(enumClass = CountryEnum.class)
    private String country;
    private String password;
    private boolean enabled;
    @DBRef
    private Set<Role> roles;

}
