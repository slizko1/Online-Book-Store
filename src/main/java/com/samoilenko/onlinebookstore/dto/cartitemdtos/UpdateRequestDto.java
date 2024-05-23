package com.samoilenko.onlinebookstore.dto.cartitemdtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record UpdateRequestDto(
        @NotNull
        @Min(0)
        int quantity
) {
}
