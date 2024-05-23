package com.samoilenko.onlinebookstore.dto;

import com.samoilenko.onlinebookstore.dto.cartitemdtos.CartItemResponseDto;
import java.util.Set;

public record ShoppingCartResponseDto(
        Long id,

        Long userId,

        Set<CartItemResponseDto> cartItems
) {
}
