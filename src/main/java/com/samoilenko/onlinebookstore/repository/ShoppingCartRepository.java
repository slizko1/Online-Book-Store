package com.samoilenko.onlinebookstore.repository;

import com.samoilenko.onlinebookstore.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

    ShoppingCart findCartByUserId(Long userId);
}
