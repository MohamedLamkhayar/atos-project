package com.example.usermongodb.enums;

public enum CountryEnum {

    FR("France"),
    MR("Maroc");

    public final String label;

    CountryEnum(String label) {
        this.label = label;
    }
}
