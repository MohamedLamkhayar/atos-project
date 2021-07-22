package com.example.usermongodb.mocks;

import com.example.usermongodb.dto.RoleDto;
import com.example.usermongodb.dto.UserDto;
import com.example.usermongodb.enums.CountryEnum;
import com.example.usermongodb.enums.RoleEnum;


import java.util.HashSet;
import java.util.Set;

public class MockeUser {

    private static UserDto userValid;
    private static UserDto userInvalid;
    private static Set<RoleDto>  roles;

    static {
        roles = new HashSet<>();
        RoleDto role = new RoleDto();
        role.setId(new Long(1));
        role.setName(RoleEnum.USER.name());
        roles.add(role);
        // valid user
        userValid = new UserDto();
        userValid.setId(new Long(1));
        userValid.setAge(18);
        userValid.setFirstName("mohamed");
        userValid.setLastName("lamkhayar");
        userValid.setCountry(CountryEnum.FR.label);
        userValid.setRoles(roles);

        // invalid user to test validation
        userInvalid = new UserDto();
        userInvalid.setAge(17);
        userInvalid.setCountry(CountryEnum.MR.label);
    }

    public static UserDto getValidUser() {return userValid ;}
    public static UserDto getInvalidUser() {return userInvalid ;}
}
