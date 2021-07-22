package com.example.usermongodb.validation;

import com.example.usermongodb.enums.CountryEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ValueOfEnumTest {

    ValueOfEnumValidator valueOfEnumValidator = new ValueOfEnumValidator();

    @BeforeEach
    public void setUp() {
        valueOfEnumValidator.acceptedValue = CountryEnum.FR.label;
    }

    /**
     * testing valueOfEnumValidator
     * case : success
     * the enum value is equal to France
     */
    @Test
    @DisplayName("Test isValid success")
    void isCountryEqualFrance() {
        Assertions.assertTrue(isValid("France"));
    }

    /**
     * testing valueOfEnumValidator
     * case : success
     * the enum value is different to France
     */
    @Test
    @DisplayName("Test isValid fail")
    void isCountryNotEqualFrance() {
        Assertions.assertFalse(isValid("Maroc"));
    }
    private boolean isValid(String value) {
        return valueOfEnumValidator.isValid(value, null);
    }
}
