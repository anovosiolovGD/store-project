package com.nvs.store.util;

import com.nvs.store.dto.CartDTO;
import com.nvs.store.dto.CartItemResponse;
import com.nvs.store.models.cart.Cart;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.function.Function;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CartUtils {
    public static final Function<Cart, CartDTO> convertToCartDto =
            cart -> CartDTO.builder()
                    .id(cart.getId())
                    .actual(cart.getActual())
                    .cartItemResponses(cart.getCartItems()
                            .stream()
                            .map(cartItem -> CartItemResponse
                                    .builder()
                                    .title(cartItem.getProduct().getTitle())
                                    .id(cartItem.getProduct().getId())
                                    .price(cartItem.getProduct().getPrice())
                                    .quantity(cartItem.getQuantity())
                                    .build())
                            .toList()
                    )
                    .build();

}
