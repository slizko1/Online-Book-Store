package com.samoilenko.onlinebookstore.dto.orderdtos;

import jakarta.validation.constraints.NotBlank;

public record PlaceOrderRequestDto(
        @NotBlank
        String shippingAddress
) {
}
