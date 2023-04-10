package com.nvs.store.repository;

import com.nvs.store.models.cart.Cart;
import com.nvs.store.models.cart.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findCartItemByCart(Cart cart);

    Optional<CartItem> findCartItemByProductId(Long id);

    void deleteCartItemByProductId(Long id);

}
