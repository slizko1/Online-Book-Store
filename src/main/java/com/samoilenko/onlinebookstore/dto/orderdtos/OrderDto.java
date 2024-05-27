package com.samoilenko.onlinebookstore.dto.orderdtos;

import com.samoilenko.onlinebookstore.model.Order;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

public record OrderDto(
        Long id,
        Long userId,
        Order.Status status,
        Set<OrderItemDto> orderItems,
        BigDecimal total,
        LocalDateTime orderDate,
        String shippingAddress
) {
}
