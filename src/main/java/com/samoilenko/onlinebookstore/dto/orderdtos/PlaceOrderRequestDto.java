package com.samoilenko.onlinebookstore.dto.orderdtos;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record PlaceOrderRequestDto(
        @NotBlank
        @Length(min = 7)
        String shippingAddress
) {
}
