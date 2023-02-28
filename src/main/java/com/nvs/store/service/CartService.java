package com.nvs.store.service;


import com.nvs.store.dto.cart.AddToCartDto;
import com.nvs.store.dto.cart.CartDto;
import com.nvs.store.dto.cart.CartItemDto;
import com.nvs.store.exceptions.CustomException;
import com.nvs.store.models.cart.Cart;
import com.nvs.store.models.product.Product;
import com.nvs.store.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class CartService {
    @Autowired
    ProductService productService;

    @Autowired
    CartRepository cartRepository;

    public void addToCart(AddToCartDto addToCartDto) {

        Product product = productService.findById(addToCartDto.getProductId());
       if (addToCartDto.getQuantity() <= product.getAvailable()) {
           Cart cart = new Cart();
           cart.setProduct(product);
           cart.setQuantity(addToCartDto.getQuantity());
           cart.setCreatedDate(new Date());

           cartRepository.save(cart);
       } throw new CustomException("We don't have enough available quantity of this product. We have only:" + product.getAvailable());
    }

    public CartDto listCartItems() {
        List<Cart> cartList = cartRepository.findAll();
        List<CartItemDto> cartItems = new ArrayList<>();
        BigDecimal totalCost = BigDecimal.ZERO;
        for (Cart cart : cartList) {
            CartItemDto cartItemDto = new CartItemDto(cart);
            BigDecimal price = cart.getProduct().getPrice();
            totalCost = totalCost.add(price.multiply(BigDecimal.valueOf(cartItemDto.getQuantity())));
            cartItems.add(cartItemDto);
        }
        CartDto cartDto1 = new CartDto();
        cartDto1.setTotalCost(totalCost);
        cartDto1.setCartItems(cartItems);
        return cartDto1;
    }

    public void updateCart(Long cartItemId, Integer quantity) {
        Optional<Cart> optionalCart = cartRepository.findById(cartItemId);
        if (optionalCart.isEmpty()) {
            throw new CustomException("cart item id is invalid: " + cartItemId);
        }
        Cart cart = optionalCart.get();
        cart.setQuantity(quantity);
        cartRepository.save(cart);
    }

    public void deleteCartItem(Long cartItemId) {

        Optional<Cart> optionalCart = cartRepository.findById(cartItemId);

        if (optionalCart.isEmpty()) {
            throw new CustomException("cart item id is invalid: " + cartItemId);
        }
        Cart cart = optionalCart.get();

        cartRepository.delete(cart);

    }


}
