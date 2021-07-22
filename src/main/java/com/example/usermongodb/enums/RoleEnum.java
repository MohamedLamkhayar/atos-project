package com.example.usermongodb.enums;

/**
 * enumeration for role's value
 */
public enum RoleEnum {

    /**
     * possible values
      */
    ADMIN("Admin"),
    USER("User");

    public final String label;

    RoleEnum(String label) {
        this.label = label;
    }
}
