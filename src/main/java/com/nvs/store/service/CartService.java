package com.nvs.store.service;

import com.nvs.store.dto.cart.CartDto;
import com.nvs.store.dto.cart.CartItem;
import com.nvs.store.dto.cart.ProductDto;
import com.nvs.store.exceptions.CustomException;
import com.nvs.store.models.product.Product;
import com.nvs.store.repository.CartItemRepository;
import com.nvs.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;



@Service
public class CartService {
    @Autowired
    ProductService productService;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CartItemRepository cartItemRepository;

    public CartDto addToCart(ProductDto productDto) {
        Product product = productService.findById(productDto.getProductId());
        if (productDto.getQuantity() > product.getAvailable()) {
            throw new CustomException("We don't have enough available quantity of this product. We have only:" + product.getAvailable());
        }
        CartItem cartItem = new CartItem();
        cartItem.setProductId(product.getId());
        cartItem.setQuantity(productDto.getQuantity());
        cartItem.setProductTitle(product.getTitle());

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

    public CartDto updateCartItems(Long itemId, Integer quantity) {
        CartItem cartItem = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new CustomException("cart item id is invalid: " + itemId));
        Product product = productService.findById(cartItem.getProductId());
        if (quantity > product.getAvailable()) {
            throw new CustomException("We don't have enough available quantity of this product. We have only:" + product.getAvailable());
        }
        cartItem.setQuantity(quantity);
        cartItemRepository.save(cartItem);
        return getAllCart();
    }

    public CartDto deleteCartItem(Long itemId) {
        CartItem cartItem = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new CustomException("cart item id is invalid: " + itemId));
        cartItemRepository.deleteById(cartItem.getCartItemId());
        return getAllCart();
    }
}

