package com.samoilenko.onlinebookstore.service;

import com.samoilenko.onlinebookstore.dto.shoppingcart.and.cartitemdtos.CartItemRequestDto;
import com.samoilenko.onlinebookstore.dto.shoppingcart.and.cartitemdtos.CartItemResponseDto;
import com.samoilenko.onlinebookstore.dto.shoppingcart.and.cartitemdtos.ShoppingCartResponseDto;
import com.samoilenko.onlinebookstore.dto.shoppingcart.and.cartitemdtos.UpdateRequestDto;
import com.samoilenko.onlinebookstore.model.User;

public interface ShoppingCartService {
    ShoppingCartResponseDto getCartByUserId(Long id);

    CartItemResponseDto addCartItem(Long user, CartItemRequestDto requestDto);

    void createCartForUser(User user);

    CartItemResponseDto update(Long cartItemId, UpdateRequestDto quantity);

    void deleteCartItemById(Long cartItemId);
}
