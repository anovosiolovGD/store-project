package com.nvs.store.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
        this.totalPrice = calculateTotalPrice();
    }

    public Long getId() {
        return id;
    }

    private BigDecimal calculateTotalPrice() {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (CartItemResponse cartItemResponse : cartItemResponses) {
            totalPrice = totalPrice.add(cartItemResponse.getSubtotal());
        }
        return totalPrice;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (CartDTO) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.actual, that.actual) &&
                Objects.equals(this.cartItemResponses, that.cartItemResponses) &&
                Objects.equals(this.totalPrice, that.totalPrice);
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
