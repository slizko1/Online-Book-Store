package com.samoilenko.onlinebookstore.dto.shoppingcart.and.cartitemdtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record UpdateRequestDto(
        @NotNull
        @Min(1)
        int quantity
) {
}
