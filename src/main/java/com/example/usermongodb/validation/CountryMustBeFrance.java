package com.example.usermongodb.validation;

import com.example.usermongodb.enums.CountryEnum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


class CountryMustBeFrance implements ConstraintValidator<ValueOfEnumCountry, CharSequence> {
    private String acceptedValue;


    @Override
    public void initialize(ValueOfEnumCountry constraintAnnotation) {
        acceptedValue = CountryEnum.FR.label;
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        return acceptedValue.equals(value.toString());
    }
}
