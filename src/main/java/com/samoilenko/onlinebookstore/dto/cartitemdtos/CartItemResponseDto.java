package com.samoilenko.onlinebookstore.dto.cartitemdtos;

public record CartItemResponseDto(
        Long id,
        Long bookId,
        String bookTitle,
        int quantity
) {
}
