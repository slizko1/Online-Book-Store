package com.samoilenko.onlinebookstore.dto.cartitemdtos;

public record CartItemRequestDto(
        Long bookId,
        int quantity
) {
}
