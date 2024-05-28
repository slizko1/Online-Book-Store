package com.samoilenko.onlinebookstore.dto.categorydtos;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CategoryRequestDto(
        @NotBlank
        @Length(min = 3)
        String name,
        String description
) {
}
