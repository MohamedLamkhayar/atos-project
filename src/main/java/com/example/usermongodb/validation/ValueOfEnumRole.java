package com.example.usermongodb.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;
import java.lang.annotation.ElementType.*;

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.FIELD, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = CountryMustBeFrance.class)
public @interface ValueOfEnumRole {
    Class<? extends Enum<?>> enumClass();
    String message() default "not knowing Role";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}