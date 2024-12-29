package com.practice.login.utils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CountryCodeValidator implements ConstraintValidator<ValidCountryCode, String> {

    @Override
    public void initialize(ValidCountryCode constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // Check if the value is null or matches the country code format
        if (value == null)  return false;

        // Regular expression to validate country code format (e.g., "+1", "+44", "+999")
        return value.matches("^\\+\\d{1,3}$");
    }
}

