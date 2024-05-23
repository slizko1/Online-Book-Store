package com.samoilenko.onlinebookstore.dto.shoppingcart.and.cartitemdtos;

import com.samoilenko.onlinebookstore.dto.shoppingcart.and.cartitemdtos.CartItemResponseDto;
import java.util.Set;

public record ShoppingCartResponseDto(
        Long id,

        Long userId,

        Set<CartItemResponseDto> cartItems
) {
}
