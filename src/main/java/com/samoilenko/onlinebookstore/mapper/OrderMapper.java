package com.samoilenko.onlinebookstore.mapper;

import com.samoilenko.onlinebookstore.config.MapperConfig;
import com.samoilenko.onlinebookstore.dto.orderdtos.OrderDto;
import com.samoilenko.onlinebookstore.model.Order;
import com.samoilenko.onlinebookstore.model.ShoppingCart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class, uses = OrderItemMapper.class)
public interface OrderMapper {

    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "orderItems", source = "cartItems")
    Order toOrderEntity(ShoppingCart usersShoppingCart);

    @Mapping(target = "userId", source = "user.id")
    OrderDto toDto(Order order);
}
