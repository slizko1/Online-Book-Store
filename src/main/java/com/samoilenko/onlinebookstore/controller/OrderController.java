package com.samoilenko.onlinebookstore.controller;

import com.samoilenko.onlinebookstore.dto.orderdtos.OrderDto;
import com.samoilenko.onlinebookstore.dto.orderdtos.OrderItemDto;
import com.samoilenko.onlinebookstore.dto.orderdtos.PlaceOrderRequestDto;
import com.samoilenko.onlinebookstore.dto.orderdtos.StatusRequestDto;
import com.samoilenko.onlinebookstore.model.User;
import com.samoilenko.onlinebookstore.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        name = "Order management",
        description = "Endpoints for managing Order and OrderItems"
)
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    @Operation(
            summary = "Create order",
            description = "Create new order with all items in user's shopping cart"
    )
    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public OrderDto placeOrder(
            @AuthenticationPrincipal User user,
            @RequestBody @Valid PlaceOrderRequestDto requestDto
    ) {
        return orderService.placeOrder(user.getId(), requestDto);
    }

    @Operation(
            summary = "Get orders",
            description = "Retrieve user's order history"
    )
    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public List<OrderDto> getAllOrders(@AuthenticationPrincipal User user, Pageable pageable) {
        return orderService.getAllOrders(user.getId(), pageable);
    }

    @Operation(
            summary = "Update order status",
            description = "Update order status by it's id"
    )
    @PreAuthorize("hasRole('USER')")
    @PatchMapping("/{orderId}")
    public OrderDto updateOrderStatus(
            @AuthenticationPrincipal User user,
            @PathVariable Long orderId,
            @RequestBody @Valid StatusRequestDto requestDto
    ) {
        return orderService.updateOrderStatus(user.getId(), orderId, requestDto);
    }

    @Operation(
            summary = "Get OrderItems   ",
            description = "Retrieve all OrderItems for a specific order"
    )
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{orderId}/items")
    public List<OrderItemDto> getOrderItems(
            @AuthenticationPrincipal User user,
            @PathVariable Long orderId
    ) {
        return orderService.getOrderItemsByOrderId(user.getId(), orderId);
    }

    @Operation(
            summary = "Get specific OrderItem",
            description = "Retrieve a specific OrderItem within an order"
    )
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{orderId}/items/{itemId}")
    public OrderItemDto getSpecificOrderItem(
            @AuthenticationPrincipal User user,
            @PathVariable Long orderId,
            @PathVariable Long itemId
    ) {
        return orderService.getOrderItem(user.getId(), orderId, itemId);
    }
}
