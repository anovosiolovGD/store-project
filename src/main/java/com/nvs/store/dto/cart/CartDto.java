package com.nvs.store.dto.cart;

import java.math.BigDecimal;
import java.util.List;

public class CartDto {
    private List<CartItem> cartItems;
    private BigDecimal totalCost;

    public CartDto() {
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }
}
