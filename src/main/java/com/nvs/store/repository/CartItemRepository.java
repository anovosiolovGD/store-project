package com.nvs.store.repository;

import com.nvs.store.dto.cart.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
//    ItemId findByProductId(Long id);
//
//    ItemId findByIdCart(Integer cartId);
}
