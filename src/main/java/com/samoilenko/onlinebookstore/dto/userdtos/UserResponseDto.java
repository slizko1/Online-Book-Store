package com.samoilenko.onlinebookstore.dto.userdtos;

public record UserResponseDto(
        Long id,
        String email,
        String firstName,
        String lastName,
        String shippingAddress
) {
}
