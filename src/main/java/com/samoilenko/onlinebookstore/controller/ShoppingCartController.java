package com.samoilenko.onlinebookstore.controller;

import com.samoilenko.onlinebookstore.dto.ShoppingCartResponseDto;
import com.samoilenko.onlinebookstore.dto.bookdtos.BookDto;
import com.samoilenko.onlinebookstore.dto.bookdtos.BookRequestDto;
import com.samoilenko.onlinebookstore.dto.cartitemdtos.CartItemRequestDto;
import com.samoilenko.onlinebookstore.dto.cartitemdtos.CartItemResponseDto;
import com.samoilenko.onlinebookstore.dto.cartitemdtos.UpdateRequestDto;
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

@Tag(name = "ShoppingCart management", description = "Endpoints for managing ShoppingCart")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/cart")
public class ShoppingCartController {
    private final ShoppingCartServiceImpl shoppingCartService; //Todo fix description to swagger

    @Operation(
            summary = "Get shopping cart",
            description = "Returns authenticated user shopping cart"
    )
    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public ShoppingCartResponseDto getShoppingCart(@AuthenticationPrincipal User user) {
        return shoppingCartService.getCartByUserId(user.getId());
    }

    @Operation(summary = "Update book", description = "Update one specific book by id")
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/items/{cartItemId}")
    public CartItemResponseDto update(
            @PathVariable Long cartItemId,
            @RequestBody UpdateRequestDto requestDto) { //todo fix validation
        return shoppingCartService.update(cartItemId, requestDto);
    }

    @Operation(summary = "Delete book", description = "Mark specific book deleted")
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/items/{cartItemId}")
    public void delete(@PathVariable Long cartItemId) {
        shoppingCartService.deleteCartItemById(cartItemId);
    }

    @Operation(
            summary = "Get shopping cart",
            description = "Returns authenticated user shopping cart"
    )
    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public CartItemResponseDto addCartItem(
            @RequestBody CartItemRequestDto requestDto,
            @AuthenticationPrincipal User user) { //TODO add validation
        return shoppingCartService.addCartItem(user.getId(), requestDto);
    }
}
