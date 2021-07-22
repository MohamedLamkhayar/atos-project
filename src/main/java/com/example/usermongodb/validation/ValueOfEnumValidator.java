package com.example.usermongodb.validation;

import com.example.usermongodb.enums.CountryEnum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

class ValueOfEnumValidator implements ConstraintValidator<ValueOfEnumCountry, CharSequence> {
    /** allow the verification of the wanted value  */
    String acceptedValue;

    /**
     * set country as acceptedValue for counrty enumeration
     * @param constraintAnnotation
     */
    @Override
    public void initialize(ValueOfEnumCountry constraintAnnotation) {
        acceptedValue = CountryEnum.FR.label;
    }

    /**
     * should return true when value equals to acceptedValue
     * @param value
     * @param context
     * @return
     */
    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        return acceptedValue.equals(value.toString());
    }
}
