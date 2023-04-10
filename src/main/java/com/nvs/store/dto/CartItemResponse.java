package com.nvs.store.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public class CartItemResponse {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("price")
    private BigDecimal price;
    @JsonProperty("quantity")
    private Integer quantity;
    @JsonProperty("subtotal")
    private BigDecimal subtotal;



    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public CartItemResponse(Long id, String title, BigDecimal price, Integer quantity) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
        this.subtotal = calculateSubtotal();
    }

    private BigDecimal calculateSubtotal() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }
}
