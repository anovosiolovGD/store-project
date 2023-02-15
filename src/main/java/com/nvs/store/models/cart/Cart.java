package com.nvs.store.models.cart;

import com.nvs.store.models.product.Product;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@RequiredArgsConstructor

public class Cart {
    @NonNull
    private Long id;
    private final List<Product> cartProducts;
    @NonNull
    private BigDecimal totalPrice;
}
