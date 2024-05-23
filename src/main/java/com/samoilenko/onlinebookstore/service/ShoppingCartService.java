package com.samoilenko.onlinebookstore.service;

import com.samoilenko.onlinebookstore.dto.ShoppingCartResponseDto;
import com.samoilenko.onlinebookstore.dto.cartitemdtos.CartItemRequestDto;
import com.samoilenko.onlinebookstore.dto.cartitemdtos.CartItemResponseDto;

public interface ShoppingCartService {
    ShoppingCartResponseDto getCartByUserId(Long id);

    CartItemResponseDto addCartItem(Long user, CartItemRequestDto requestDto);
}
