package com.samoilenko.onlinebookstore.validation.constraints;

import com.samoilenko.onlinebookstore.dto.UserRegistrationRequestDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FieldMatchValidator implements
        ConstraintValidator<FieldMatch, UserRegistrationRequestDto> {

    @Override
    public void initialize(FieldMatch constraintAnnotation) {
    }

    @Override
    public boolean isValid(UserRegistrationRequestDto userRequest,
                           ConstraintValidatorContext context) {
        return userRequest.getPassword().equals(userRequest.getRepeatPassword());
    }
}
