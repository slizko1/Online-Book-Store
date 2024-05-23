package com.samoilenko.onlinebookstore.service.impl;

import com.samoilenko.onlinebookstore.dto.ShoppingCartResponseDto;
import com.samoilenko.onlinebookstore.dto.cartitemdtos.CartItemRequestDto;
import com.samoilenko.onlinebookstore.dto.cartitemdtos.CartItemResponseDto;
import com.samoilenko.onlinebookstore.mapper.CartItemMapper;
import com.samoilenko.onlinebookstore.mapper.ShoppingCartMapper;
import com.samoilenko.onlinebookstore.model.CartItem;
import com.samoilenko.onlinebookstore.repository.CartItemRepository;
import com.samoilenko.onlinebookstore.repository.ShoppingCartRepository;
import com.samoilenko.onlinebookstore.service.ShoppingCartService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final UserServiceImpl userService;
    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartMapper shoppingCartMapper;
    private final CartItemMapper cartItemMapper;
    private final CartItemRepository cartItemRepository;

    @Override
    @Transactional
    public ShoppingCartResponseDto getCartByUserId(Long id) {
        return shoppingCartMapper.toResponseDto(
                shoppingCartRepository.findCartByUserId(id)
        );
    }

    @Override
    public CartItemResponseDto addCartItem(Long userId, CartItemRequestDto requestDto) {
        CartItem cartItem = cartItemMapper.toEntity(requestDto);
        cartItem.setShoppingCart(shoppingCartRepository.findCartByUserId(userId));
        return cartItemMapper.toResponseDto(cartItemRepository.save(cartItem));
    }


}
