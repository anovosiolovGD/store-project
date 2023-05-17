package com.nvs.store.exceptions;

public class ProductNotExistsException extends IllegalArgumentException {
    private final Long productId;

    public ProductNotExistsException(Long productId) {
        super();
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;

    }
}
