package com.example.validation.validator;

import com.example.validation.customvalidation.ValidAddress;
import com.example.validation.dto.Address;
import com.example.validation.dto.StateFormat;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Objects.isNull;
import static org.apache.logging.log4j.util.Strings.isBlank;

public class AddressValidator implements ConstraintValidator<ValidAddress, Address> {

    private static final Map<StateFormat, Pattern> STATE_FORMAT_REGEX =
            Map.of(StateFormat.ANSI, Pattern.compile("^[A-Z]{2}$"),
                    StateFormat.ISO, Pattern.compile("^[A-Z]{2}-[A-Z]{2}$"));

    private StateFormat stateFormat;
    @Override
    public void initialize(ValidAddress constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.stateFormat = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(Address address, ConstraintValidatorContext constraintValidatorContext) {
        if (isNull(address)) return false;
        if (isBlank(address.getName()) || isBlank(address.getCity())) return false;
        Matcher matcher = STATE_FORMAT_REGEX.get(stateFormat)
                .matcher(address.getState());
        if (!matcher.matches()){
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("Invalid State Name; Please use the " + stateFormat + " format")
                    .addPropertyNode("state")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
