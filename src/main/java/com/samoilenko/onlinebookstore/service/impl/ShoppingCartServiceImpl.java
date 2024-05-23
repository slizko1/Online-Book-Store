package com.samoilenko.onlinebookstore.service.impl;

import com.samoilenko.onlinebookstore.dto.ShoppingCartResponseDto;
import com.samoilenko.onlinebookstore.dto.cartitemdtos.CartItemRequestDto;
import com.samoilenko.onlinebookstore.dto.cartitemdtos.CartItemResponseDto;
import com.samoilenko.onlinebookstore.dto.cartitemdtos.UpdateRequestDto;
import com.samoilenko.onlinebookstore.exception.EntityNotFoundException;
import com.samoilenko.onlinebookstore.mapper.CartItemMapper;
import com.samoilenko.onlinebookstore.mapper.ShoppingCartMapper;
import com.samoilenko.onlinebookstore.model.CartItem;
import com.samoilenko.onlinebookstore.model.ShoppingCart;
import com.samoilenko.onlinebookstore.model.User;
import com.samoilenko.onlinebookstore.repository.BookRepository;
import com.samoilenko.onlinebookstore.repository.CartItemRepository;
import com.samoilenko.onlinebookstore.repository.ShoppingCartRepository;
import com.samoilenko.onlinebookstore.service.ShoppingCartService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final BookRepository bookRepository;
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
        cartItem.setBook(bookRepository.findById(requestDto.bookId()).orElseThrow(
                () -> new EntityNotFoundException("Can't find book by id " + requestDto.bookId())
        ));
        cartItem.setShoppingCart(shoppingCartRepository.findCartByUserId(userId));
        return cartItemMapper.toResponseDto(cartItemRepository.save(cartItem));
    }

    @Override
    public void createCartForUser(User savedUser) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(savedUser);
        shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public CartItemResponseDto update(Long cartItemId, UpdateRequestDto requestDto) {
        CartItem itemToUpdate = cartItemRepository.findById(cartItemId).orElseThrow(
                () -> new EntityNotFoundException("Cart item with id " + cartItemId + " not found")
        );
        itemToUpdate.setQuantity(requestDto.quantity());
        return cartItemMapper.toResponseDto(cartItemRepository.save(itemToUpdate));
    }

    @Override
    public void deleteCartItemById(Long cartItemId) {
        validateId(cartItemId);
        cartItemRepository.deleteById(cartItemId);
    }

    private void validateId(Long id) {
        if (!cartItemRepository.existsById(id)) {
            throw new EntityNotFoundException("Cart item with id " + id + " not found");
        }

    }
}
