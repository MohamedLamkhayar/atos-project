package com.example.usermongodb.dto;

import com.example.usermongodb.enums.CountryEnum;
import com.example.usermongodb.validation.ValueOfEnumCountry;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class UserDto {
    /** id of the userDto */
    private  Long id;
    /** lastName of the userDto */
    @NotBlank(message = "lastName is mandatory")
    @Size(max = 100, message = "only 100 is allowed")
    private String lastName;
    /** firstName of the userDto */
    @NotBlank(message = "firstName is mandatory")
    @Size(max = 100, message = "only 100 is allowed")
    private String firstName;
    /** age of the userDto */
    @Min(value = 18 , message = "user must have an age bigger than 18")
    private Integer age;
    /** country of the userDto */
    @ValueOfEnumCountry(enumClass = CountryEnum.class)
    private String country;
    /** password of the userDto */
    private String password;
    /** detect if the userDto is active */
    private boolean enabled;
    /** list of roleDtos of the userDto */
    private Set<RoleDto> roles;
}
