package com.samoilenko.onlinebookstore.service.impl;

import com.samoilenko.onlinebookstore.dto.shoppingcart.and.cartitemdtos.CartItemRequestDto;
import com.samoilenko.onlinebookstore.dto.shoppingcart.and.cartitemdtos.CartItemResponseDto;
import com.samoilenko.onlinebookstore.dto.shoppingcart.and.cartitemdtos.ShoppingCartResponseDto;
import com.samoilenko.onlinebookstore.dto.shoppingcart.and.cartitemdtos.UpdateRequestDto;
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
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final BookRepository bookRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartMapper shoppingCartMapper;
    private final CartItemMapper cartItemMapper;
    private final CartItemRepository cartItemRepository;

    @Override
    @Transactional(readOnly = true)
    public ShoppingCartResponseDto getCartByUserId(Long id) {
        return shoppingCartMapper.toResponseDto(
                shoppingCartRepository.findCartByUserId(id)
        );
    }

    @Override
    @Transactional
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
    @Transactional
    public CartItemResponseDto update(Long userId, Long cartItemId, UpdateRequestDto requestDto) {
        CartItem itemToUpdate = getCartItem(cartItemId);
        checkUserOwnershipOfItem(userId, itemToUpdate);
        itemToUpdate.setQuantity(requestDto.quantity());
        return cartItemMapper.toResponseDto(cartItemRepository.save(itemToUpdate));
    }

    @Override
    @Transactional
    public void deleteCartItemById(Long userId, Long cartItemId) {
        CartItem cartItemToDelete = getCartItem(cartItemId);
        checkUserOwnershipOfItem(userId, cartItemToDelete);
        cartItemRepository.deleteById(cartItemId);
    }

    private CartItem getCartItem(Long cartItemId) {
        return cartItemRepository.findById(cartItemId).orElseThrow(
                () -> new EntityNotFoundException("Cart item with id " + cartItemId + " not found")
        );
    }

    private void checkUserOwnershipOfItem(Long userId, CartItem cartItem) {
        Long shoppingCartId = shoppingCartRepository.findCartByUserId(userId).getId();
        if (shoppingCartId != cartItem.getShoppingCart().getId()) {
            throw new AccessDeniedException(
                    "User does not have permission to modify this cart item"
            );
        }
    }
}
