package com.samoilenko.onlinebookstore.service;

import com.samoilenko.onlinebookstore.dto.orderdtos.OrderDto;
import com.samoilenko.onlinebookstore.dto.orderdtos.OrderItemDto;
import com.samoilenko.onlinebookstore.dto.orderdtos.PlaceOrderRequestDto;
import com.samoilenko.onlinebookstore.dto.orderdtos.StatusRequestDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    OrderDto placeOrder(Long userId, PlaceOrderRequestDto requestDto);

    List<OrderDto> getAllOrders(Long userId, Pageable pageable);

    OrderDto updateOrderStatus(Long userId, Long orderId, StatusRequestDto requestDto);

    List<OrderItemDto> getOrderItemsByOrderId(Long userId, Long orderId);

    OrderItemDto getOrderItem(Long userId, Long orderId, Long itemId);
}
