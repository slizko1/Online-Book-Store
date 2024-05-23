package com.samoilenko.onlinebookstore.dto.shoppingcart.and.cartitemdtos;

public record CartItemResponseDto(
        Long id,
        Long bookId,
        String bookTitle,
        int quantity
) {
}
