package com.nvs.store.dto.cart;

import jakarta.persistence.*;

@Entity
@Table(name = "cart_items")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemId;

    private Long productId;

    private String productTitle;

    private int quantity;

    public CartItem(Long cartItemId, Long productId, String productTitle, int quantity) {
        this.cartItemId = cartItemId;
        this.productId = productId;
        this.productTitle = productTitle;
        this.quantity = quantity;
    }

    public CartItem() {

    }

    public Long getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(Long cartId) {
        this.cartItemId = cartId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

