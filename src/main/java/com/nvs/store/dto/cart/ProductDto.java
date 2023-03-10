package com.nvs.store.dto.cart;

import javax.validation.constraints.NotNull;

public class ProductDto {
    @NotNull
    private Long productId;
    @NotNull
    private int quantity;

    public ProductDto(Long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

