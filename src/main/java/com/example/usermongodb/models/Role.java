package com.example.usermongodb.models;

import com.example.usermongodb.enums.CountryEnum;
import com.example.usermongodb.enums.RoleEnum;
import com.example.usermongodb.validation.ValueOfEnumRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "roles")
@AllArgsConstructor
@Data
public class Role {

    @Transient
    public static final String SEQUENCE_NAME = "roles_sequence";

    @Id
    private Long id;

    @ValueOfEnumRole(enumClass = RoleEnum.class)
    private String name;
}
