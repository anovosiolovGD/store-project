package com.nvs.store.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nvs.store.util.CartUtils;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;


@Builder
public final class CartDTO {

    @JsonProperty("id")
    private final Long id;

    @JsonProperty("actual")
    private final Boolean actual;

    @JsonProperty("cartItems")
    private final List<CartItemResponse> cartItemResponses;

    @JsonProperty("totalPrice")
    private final BigDecimal totalPrice;

    public CartDTO(
            Long id,
            Boolean actual,
            List<CartItemResponse> cartItemResponses
    ) {
        this.id = id;
        this.actual = actual;
        this.cartItemResponses = cartItemResponses;
        this.totalPrice = CartUtils.calculateTotalPrice(cartItemResponses);
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CartDTO cartDTO)) return false;
        return Objects.equals(id, cartDTO.id) &&
                Objects.equals(actual, cartDTO.actual) &&
                Objects.equals(cartItemResponses, cartDTO.cartItemResponses) &&
                Objects.equals(totalPrice, cartDTO.totalPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, actual, cartItemResponses, totalPrice);
    }

    @Override
    public String toString() {
        return "CartDTO[" +
                "id=" + id + ", " +
                "actual=" + actual + ", " +
                "cartItems=" + cartItemResponses + ", " +
                "totalPrice=" + totalPrice + ']';
    }

}
