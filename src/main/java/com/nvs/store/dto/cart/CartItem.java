package com.nvs.store.dto.cart;

import jakarta.persistence.*;

@Entity
@Table(name = "cart_items")
public class CartItem {

    @EmbeddedId
    private CartItemId id;

    private int quantity;


    public CartItem() {

    }

    public CartItem(Long cartId, Long productId, int quantity) {
        this.id = new CartItemId(cartId,productId);
        this.quantity = quantity;
    }
    public Long getProductId(){
        return id.getProductId();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

