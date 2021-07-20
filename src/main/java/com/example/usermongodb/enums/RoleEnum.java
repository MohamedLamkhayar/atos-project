package com.example.usermongodb.enums;

public enum RoleEnum {


    ADMIN("Admin"),
    USER("User");

    public final String label;

    RoleEnum(String label) {
        this.label = label;
    }
}
