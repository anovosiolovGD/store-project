package com.nvs.store.controllers;

import com.nvs.store.dto.cart.CartDto;
import com.nvs.store.dto.cart.ProductDto;
import com.nvs.store.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/carts")
public class CartController {

    private final CartService cartService;


    @PostMapping()
    @ResponseStatus(value = HttpStatus.CREATED)
    public CartDto addToCart(@RequestBody ProductDto productDto) {
        return cartService.addToCart(productDto);
    }

    @GetMapping
    public CartDto getAllCartItems() {
        return cartService.getAllCart();
    }
    @PutMapping("/{productId}")
    public CartDto updateCartItem(@PathVariable ("productId")Long productId, @RequestParam Integer quantity) {
        return cartService.updateCartItems(productId, quantity);
    }
    @DeleteMapping("/{productId}")
    public CartDto deleteCartItem(@PathVariable Long productId) {
        return cartService.deleteCartItem(productId);
    }
}
