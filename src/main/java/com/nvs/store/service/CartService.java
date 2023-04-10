package com.nvs.store.service;

import com.nvs.store.dto.*;
import com.nvs.store.models.cart.Cart;
import com.nvs.store.models.cart.CartItem;
import com.nvs.store.models.product.Product;
import com.nvs.store.models.user.User;
import com.nvs.store.repository.*;
import com.nvs.store.util.CartUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public CartDTO findActualCartByEmail() {
        String userEmail = getUserEmailFromContext();
        User user = userRepository
                .findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException(
                        String.format("User with email [%s] not found", userEmail))
                );
        Cart cart = cartRepository
                .findByUserIdAndActualTrue(user.getId());
        CartDTO cartDTO;
        if (cart == null) {
            Cart newCart = Cart.builder()
                    .user(user)
                    .actual(true)
                    .cartItems(List.of())
                    .build();
            cartRepository.save(newCart);
            cartDTO = CartUtils.convertToCartDto.apply(newCart);
            return cartDTO;
        }
        cartDTO = CartUtils.convertToCartDto.apply(cart);
        return cartDTO;
    }

    public List<CartDTO> getAllCarts() {
        String userEmail = getUserEmailFromContext();
        User user = userRepository
                .findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException(
                        String.format("User with email [%s] not found", userEmail))
                );
        return cartRepository
                .findByUserId(user.getId())
                .stream()
                .map(CartUtils.convertToCartDto)
                .toList();
    }

    public CartDTO addProductToCart(CartItemRequest cartItemRequest) {
        String userEmail = getUserEmailFromContext();
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException(
                        String.format("User with username %s not found", userEmail))
                );

        Product product = productRepository
                .findById(cartItemRequest
                        .getProductId())
                .orElseThrow(() ->
                        new RuntimeException(
                                String.format("Product with id [%d] does not exist", cartItemRequest.getProductId()))
                );
        Cart cart = cartRepository
                .findByUserIdAndActualTrue(user.getId());

        List<CartItem> cartItems = cartItemRepository.findCartItemByCart(cart);
        for (CartItem cartItem : cartItems) {
            if (cartItem.getProduct().getId().equals(product.getId())) {
                int newQuantity = cartItem.getQuantity() + cartItemRequest.getQuantity();
                validateAvailableProductQuantities(product, newQuantity);
                cartItem.setQuantity(newQuantity);
                cartItemRepository.save(cartItem);
                return CartUtils.convertToCartDto.apply(cartRepository.findByUserIdAndActualTrue(user.getId()));
            }
        }

        validateAvailableProductQuantities(product, cartItemRequest.getQuantity());
        CartItem cartItem = CartItem
                .builder()
                .quantity(cartItemRequest.getQuantity())
                .product(product)
                .cart(cart)
                .build();

        cartItemRepository.save(cartItem);

        return CartUtils.convertToCartDto
                .apply(cartRepository
                        .findByUserIdAndActualTrue(user.getId()));
    }

    public CartDTO orderCart() {
        String userEmail = getUserEmailFromContext();
        User user = userRepository.findByEmail(userEmail).orElseThrow(
                () -> new RuntimeException(
                        String.format("User with username %s not found", userEmail))
        );

        Cart cart = cartRepository
                .findByUserIdAndActualTrue(user.getId());

        List<CartItem> orderedCartItems = cartItemRepository.findCartItemByCart(cart);

        for (CartItem cartItem : orderedCartItems) {
            Product product = productRepository.findById(cartItem.getProduct().getId())
                    .orElseThrow(() -> new RuntimeException(
                            String.format("Product with id [%d] does not exist", cartItem.getProduct().getId()))
                    );
            if (product.getAvailable() < 1) {
                throw new RuntimeException(
                        String.format("Sorry, [%s] is not available yet", product.getTitle())
                );
            } else if (cartItem.getQuantity() > product.getAvailable()) {
                throw new RuntimeException(
                        String.format(
                                "Only [%d] of [%s] available. Please order available quantity.",
                                product.getAvailable(),
                                product.getTitle())
                );
            }
        }

        cart.setActual(false);
        cartRepository.save(cart);

        Cart newCart = Cart.builder()
                .user(user)
                .actual(true)
                .cartItems(List.of())
                .build();

        return CartUtils.convertToCartDto
                .apply(cartRepository
                        .save(newCart));
    }


    public CartDTO updateProduct(Long id, Integer quantity) {
        String userEmail = getUserEmailFromContext();
        User user = userRepository.findByEmail(userEmail).orElseThrow(
                () -> new RuntimeException(
                        String.format("User with username %s not found", userEmail))
        );
        CartItem cartItem = cartItemRepository.findCartItemByProductId(id)
                .orElseThrow(() -> new RuntimeException("No such product in cart"));

        CartItemRequest cartItemRequest = CartItemRequest.builder()
                .productId(id)
                .quantity(quantity)
                .build();
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        String.format("Product with id [%d] does not exist", id))
                );
        validateAvailableProductQuantities(product, cartItemRequest.getQuantity());
        cartItem.setQuantity(quantity);
        cartItemRepository.save(cartItem);
        return CartUtils.convertToCartDto
                .apply(cartRepository
                        .findByUserIdAndActualTrue(user.getId()));
    }

    @Transactional
    public void deleteProduct(Long id) {
        cartItemRepository.deleteCartItemByProductId(id);
    }

    private String getUserEmailFromContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    private void validateAvailableProductQuantities(Product product, Integer quantity) {
        if (product.getAvailable() < 1) {
            throw new RuntimeException(
                    String.format("Sorry, [%s] is not available yet", product.getTitle())
            );
        } else if (quantity > product.getAvailable()) {
            throw new RuntimeException(
                    String.format(
                            "Only [%d] of [%s] available. Please order available quantity.",
                            product.getAvailable(),
                            product.getTitle())
            );
        }
    }
}
