package com.samoilenko.onlinebookstore.service.impl;

import com.samoilenko.onlinebookstore.dto.orderdtos.OrderDto;
import com.samoilenko.onlinebookstore.dto.orderdtos.OrderItemDto;
import com.samoilenko.onlinebookstore.dto.orderdtos.PlaceOrderRequestDto;
import com.samoilenko.onlinebookstore.dto.orderdtos.StatusRequestDto;
import com.samoilenko.onlinebookstore.exception.EntityNotFoundException;
import com.samoilenko.onlinebookstore.mapper.OrderItemMapper;
import com.samoilenko.onlinebookstore.mapper.OrderMapper;
import com.samoilenko.onlinebookstore.model.Order;
import com.samoilenko.onlinebookstore.model.OrderItem;
import com.samoilenko.onlinebookstore.model.ShoppingCart;
import com.samoilenko.onlinebookstore.repository.CartItemRepository;
import com.samoilenko.onlinebookstore.repository.OrderItemRepository;
import com.samoilenko.onlinebookstore.repository.OrderRepository;
import com.samoilenko.onlinebookstore.repository.ShoppingCartRepository;
import com.samoilenko.onlinebookstore.service.OrderService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    private final OrderItemMapper orderItemMapper;
    private final ShoppingCartRepository shoppingCartRepository;
    private final OrderItemRepository orderItemRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    @Transactional
    public OrderDto placeOrder(Long userId, PlaceOrderRequestDto requestDto) {
        ShoppingCart usersShoppingCart = shoppingCartRepository.findCartByUserId(userId);
        Order newOrder = orderMapper.toOrderEntity(usersShoppingCart);
        newOrder.setStatus(Order.Status.PENDING);
        newOrder.setOrderDate(LocalDateTime.now());
        Set<OrderItem> orderItems = newOrder.getOrderItems();
        BigDecimal total = BigDecimal.ZERO;
        for (OrderItem orderItem : orderItems) {
            orderItem.setOrder(newOrder);
            total = total.add(
                    orderItem.getPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity()))
            );
        }
        newOrder.setTotal(total);
        newOrder.setShippingAddress(requestDto.shippingAddress());
        Order savedOrder = orderRepository.save(newOrder);
        usersShoppingCart.getCartItems().clear();
        cartItemRepository.deleteAllByShoppingCartId(usersShoppingCart.getId());
        return orderMapper.toDto(savedOrder);
    }

    @Override
    @Transactional
    public List<OrderDto> getAllOrders(Long userId, Pageable pageable) {
        return orderRepository.getAllOrdersByUserId(userId, pageable).stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public OrderDto updateOrderStatus(Long userId, Long orderId, StatusRequestDto requestDto) {
        Order order = getOrder(orderId);
        checkUserOwnershipOfOrder(userId, order);
        order.setStatus(requestDto.getStatus());
        return orderMapper.toDto(orderRepository.save(order));
    }

    @Override
    @Transactional
    public List<OrderItemDto> getOrderItemsByOrderId(Long userId, Long orderId) {
        Order order = getOrder(orderId);
        checkUserOwnershipOfOrder(userId, order);
        return order.getOrderItems().stream()
                .map(orderItemMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public OrderItemDto getOrderItem(Long userId, Long orderId, Long itemId) {
        Order order = getOrder(orderId);
        checkUserOwnershipOfOrder(userId, order);
        OrderItem orderItem = orderItemRepository.findById(itemId).orElseThrow(
                () -> new EntityNotFoundException("OrderItem with id " + itemId + " not found")
        );
        if (order.getId() != orderItem.getOrder().getId()) {
            throw new AccessDeniedException(
                    "User does not have permission to get this orderItem"
            );
        }
        return orderItemMapper.toDto(orderItem);
    }

    private static void checkUserOwnershipOfOrder(Long userId, Order order) {
        if (order.getUser().getId() != userId) {
            throw new AccessDeniedException(
                    "User does not have permission to get this order"
            );
        }
    }

    private Order getOrder(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(
                () -> new EntityNotFoundException("Order with id " + orderId + " not found")
        );
    }
}
