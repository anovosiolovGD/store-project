package com.nvs.store.dto.cart;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;
@Embeddable
public class CartItemId implements Serializable {
    private Long productId;
    private Long cartId;

    protected CartItemId(){

    }

    public CartItemId(Long productId, Long cartId) {
        this.productId = productId;
        this.cartId = cartId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItemId that = (CartItemId) o;
        return productId.equals(that.productId) && cartId.equals(that.cartId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, cartId);
    }
}
