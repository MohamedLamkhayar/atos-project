package com.example.usermongodb.enums;

/**
 * enumeration for country's value
 */
public enum CountryEnum {

    /**
     * possible values
     */
    FR("France"),
    MR("Maroc");

    public final String label;

    CountryEnum(String label) {
        this.label = label;
    }
}
