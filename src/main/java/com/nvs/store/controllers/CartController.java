package com.nvs.store.controllers;

import com.nvs.store.dto.CartDTO;
import com.nvs.store.dto.CartItemRequest;
import com.nvs.store.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/carts")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping
    public ResponseEntity<CartDTO> getActualCart() {
        return ResponseEntity.ok(cartService.findActualCartByEmail());
    }

    @GetMapping("/all")
    public ResponseEntity<List<CartDTO>> getAllCarts() {
        return ResponseEntity.ok(cartService.getAllCarts());
    }


    @PostMapping
    public ResponseEntity<CartDTO> addProductToCart(@RequestBody CartItemRequest cartItemRequest) {
        return ResponseEntity.ok(cartService.addProductToCart(cartItemRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartDTO> updateProductQuantity(@PathVariable Long id, @RequestParam Integer quantity) {
        return ResponseEntity.ok(cartService.updateProduct(id, quantity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProductFromCart(@PathVariable Long id) {
        cartService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/order")
    public ResponseEntity<CartDTO> orderCart() {
        return ResponseEntity.ok(cartService.orderCart());
    }


}
