package com.nvs.store.service;

import com.nvs.store.dto.cart.CartDto;
import com.nvs.store.dto.cart.CartItem;
import com.nvs.store.dto.cart.ProductDto;
import com.nvs.store.exceptions.InvalidCartItemException;
import com.nvs.store.exceptions.NotAvailableException;
import com.nvs.store.models.product.Product;
import com.nvs.store.repository.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor()
public class CartService {

    private final ProductService productService;

    private final CartItemRepository cartItemRepository;


    public CartDto addToCart(ProductDto productDto) {
        Product product = productService.findById(productDto.getProductId());
        if (productDto.getQuantity() > product.getAvailable()) {
            throw new NotAvailableException("We don't have enough available quantity of this product. We have only:" + product.getAvailable());
        }
        CartItem cartItem = new CartItem();
        cartItem.setQuantity(productDto.getQuantity());

        cartItemRepository.save(cartItem);
        return getAllCart();
    }

    public CartDto getAllCart() {
        List<CartItem> cartItemsList = cartItemRepository.findAll();
        BigDecimal totalCost = BigDecimal.ZERO;
        for (CartItem cartItem : cartItemsList) {
            BigDecimal price = productService.findById(cartItem.getProductId()).getPrice();
            totalCost = totalCost.add(price.multiply(BigDecimal.valueOf(cartItem.getQuantity())));
        }
        CartDto cartDto = new CartDto();
        cartDto.setCartItems(cartItemsList);
        cartDto.setTotalCost(totalCost);
        return cartDto;
    }

    public CartDto updateCartItems(Long productId, Integer quantity) {
        CartItem cartItem = cartItemRepository.findById(productId)
                .orElseThrow(() -> new InvalidCartItemException("cart item id is invalid: " + productId));
        Product product = productService.findById(cartItem.getProductId());
        if (quantity > product.getAvailable()) {
            throw new InvalidCartItemException("We don't have enough available quantity of this product. We have only:" + product.getAvailable());
        }
        cartItem.setQuantity(quantity);
        cartItemRepository.save(cartItem);
        return getAllCart();
    }

    public CartDto deleteCartItem(Long productId) {
        CartItem cartItem = cartItemRepository.findById(productId)
                .orElseThrow(() -> new InvalidCartItemException("cart item id is invalid: " + productId));
        cartItemRepository.deleteById(cartItem.getProductId());
        return getAllCart();
    }
}

