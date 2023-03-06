package com.nvs.store.controllers;

import com.nvs.store.dto.cart.CartDto;
import com.nvs.store.dto.cart.ProductDto;
import com.nvs.store.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/auth/carts")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping()
    public ResponseEntity<CartDto> addToCart(@RequestBody ProductDto productDto) {
        return ResponseEntity.status(CREATED).body(cartService.addToCart(productDto));
    }

    @GetMapping
    public ResponseEntity<CartDto> getAllCartItems() {
        return ResponseEntity.ok(cartService.getAllCart());
    }
    @PutMapping("/{itemId}")
    public ResponseEntity<CartDto> updateCartItem(@PathVariable ("itemId")Long itemId, @RequestParam Integer quantity) {
        return ResponseEntity.ok(cartService.updateCartItems(itemId, quantity));
    }
    @DeleteMapping("/{productId}")
    public ResponseEntity<CartDto> deleteCartItem(@PathVariable Long productId) {
        return ResponseEntity.ok(cartService.deleteCartItem(productId));
    }
}
