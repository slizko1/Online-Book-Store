package com.samoilenko.onlinebookstore.validation.constraints;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.util.Objects;

public class FieldMatchValidator implements
        ConstraintValidator<FieldMatch, Object> {
    private String first;
    private String second;

    @Override
    public void initialize(FieldMatch constraintAnnotation) {
        first = constraintAnnotation.first();
        second = constraintAnnotation.second();
    }

    @Override
    public boolean isValid(Object fieldsSource,
                           ConstraintValidatorContext context) {
        try {
            Field firstField = fieldsSource.getClass().getDeclaredField(first);
            Field secondField = fieldsSource.getClass().getDeclaredField(second);
            firstField.setAccessible(true);
            secondField.setAccessible(true);

            Object firstValue = firstField.get(fieldsSource);
            Object secondValue = secondField.get(fieldsSource);
            return Objects.equals(firstValue, secondValue);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid field names were passed to the "
                    + "@FieldMatch params, validator was unable to get them or access to them, "
                    + "field names received: " + first + " and " + second);

        }

    }
}
