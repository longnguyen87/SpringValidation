package com.example.validation.customvalidation;

import com.example.validation.dto.StateFormat;
import com.example.validation.validator.AddressValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = {AddressValidator.class})
public @interface ValidAddress {
    String message() default "Invalid or null Address provided";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    StateFormat value();
}
