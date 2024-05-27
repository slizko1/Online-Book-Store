package com.samoilenko.onlinebookstore.dto.orderdtos;

public record OrderItemDto(
        Long id,
        Long bookId,
        Integer quantity
) {
}
