package com.example.usermongodb.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.FIELD, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = CountryMustBeFrance.class)
public @interface ValueOfEnumCountry {
    Class<? extends Enum<?>> enumClass();

    String message() default "user must be from france";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
