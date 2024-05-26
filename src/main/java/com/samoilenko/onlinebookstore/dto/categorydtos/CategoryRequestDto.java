package com.samoilenko.onlinebookstore.dto.categorydtos;

import jakarta.validation.constraints.NotNull;

public record CategoryRequestDto(
        @NotNull
        String name,
        String description
) {}
