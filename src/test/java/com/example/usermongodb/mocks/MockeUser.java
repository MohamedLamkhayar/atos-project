package com.example.usermongodb.mocks;

import com.example.usermongodb.enums.CountryEnum;
import com.example.usermongodb.enums.RoleEnum;
import com.example.usermongodb.models.Role;
import com.example.usermongodb.models.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MockeUser {

    private static User userValid;
    private static User userInvalid;
    private static Set<Role>  roles;

    static {
        roles = new HashSet<>();
        roles.add(new Role(new Long(1) , RoleEnum.USER.name()));
        // valid user
        userValid = new User();
        userValid.setId(new Long(1));
        userValid.setAge(18);
        userValid.setFirstName("mohamed");
        userValid.setLastName("lamkhayar");
        userValid.setCountry(CountryEnum.FR.label);
        userValid.setRoles(roles);

        // invalid user to test validation
        userInvalid = new User();
        userInvalid.setAge(17);
        userInvalid.setCountry(CountryEnum.MR.label);
    }

    public static User getValidUser() {return userValid ;}
    public static User getInvalidUser() {return userInvalid ;}
}
