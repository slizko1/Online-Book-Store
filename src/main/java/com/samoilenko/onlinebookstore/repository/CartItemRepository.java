package com.samoilenko.onlinebookstore.repository;

import com.samoilenko.onlinebookstore.model.CartItem;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    @Query("DELETE FROM CartItem c WHERE c.shoppingCart.id = :shoppingCartId")
    void deleteAllByShoppingCartId(Long shoppingCartId);
}
