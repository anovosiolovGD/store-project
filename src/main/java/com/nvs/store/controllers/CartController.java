package com.nvs.store.controllers;


import com.nvs.store.common.ApiResponse;
import com.nvs.store.dto.cart.AddToCartDto;
import com.nvs.store.dto.cart.CartDto;
import com.nvs.store.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping()
    public ResponseEntity<ApiResponse> addToCart(@RequestBody AddToCartDto addToCartDto) {

        cartService.addToCart(addToCartDto);

        return new ResponseEntity<>(new ApiResponse(true, "Added to cart"), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<CartDto> getCartItems() {
        CartDto cartDto = cartService.listCartItems();
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }

    @PutMapping("{cartItemId}")
    public  ResponseEntity<ApiResponse> updateCartItem (@PathVariable ("cartItemId") Long itemId,
                                                        @RequestParam Integer quantity){
        cartService.updateCart(itemId, quantity);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Cart item has been updated"), HttpStatus.OK);
    }

    @DeleteMapping ("{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable("cartItemId") Long itemId) {

        cartService.deleteCartItem(itemId);

        return new ResponseEntity<>(new ApiResponse(true, "Item has been removed"), HttpStatus.OK);

    }
}
