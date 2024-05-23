package com.samoilenko.onlinebookstore.controller;

import com.samoilenko.onlinebookstore.dto.shoppingcart.and.cartitemdtos.CartItemRequestDto;
import com.samoilenko.onlinebookstore.dto.shoppingcart.and.cartitemdtos.CartItemResponseDto;
import com.samoilenko.onlinebookstore.dto.shoppingcart.and.cartitemdtos.ShoppingCartResponseDto;
import com.samoilenko.onlinebookstore.dto.shoppingcart.and.cartitemdtos.UpdateRequestDto;
import com.samoilenko.onlinebookstore.model.User;
import com.samoilenko.onlinebookstore.service.impl.ShoppingCartServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        name = "ShoppingCart management",
        description = "Endpoints for managing ShoppingCart and CartItems"
)
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/cart")
public class ShoppingCartController {
    private final ShoppingCartServiceImpl shoppingCartService;

    @Operation(
            summary = "Get shopping cart",
            description = "Returns the authenticated user's shopping cart"
    )
    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public ShoppingCartResponseDto getShoppingCart(@AuthenticationPrincipal User user) {
        return shoppingCartService.getCartByUserId(user.getId());
    }

    @Operation(
            summary = "Add cart item",
            description = "Add cart item to the user`s shopping cart"
    )
    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public CartItemResponseDto addCartItem(
            @RequestBody @Valid CartItemRequestDto requestDto,
            @AuthenticationPrincipal User user) {
        return shoppingCartService.addCartItem(user.getId(), requestDto);
    }

    @Operation(
            summary = "Update cart item",
            description = "Update one specific cart item by id in user`s shopping cart")
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/items/{cartItemId}")
    public CartItemResponseDto update(
            @PathVariable Long cartItemId,
            @RequestBody @Valid UpdateRequestDto requestDto) {
        return shoppingCartService.update(cartItemId, requestDto);
    }

    @Operation(
            summary = "Delete cart item",
            description = "Delete specific cart item from user`s shopping cart")
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/items/{cartItemId}")
    public void delete(@PathVariable Long cartItemId) {
        shoppingCartService.deleteCartItemById(cartItemId);
    }
}
