package com.samoilenko.onlinebookstore.dto.categorydtos;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequestDto(
        @NotBlank
        String name,
        String description
) {
}
