package com.samoilenko.onlinebookstore.validation.constraints;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;

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

            return firstValue == null && secondValue == null
                    || firstValue != null && firstValue.equals(secondValue);
        } catch (Exception e) {
            return false;
        }

    }
}
