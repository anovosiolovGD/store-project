package com.nvs.store.controllers;

import com.nvs.store.models.product.Product;
import com.nvs.store.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1/auth/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<String> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        return productService.getProduct(id);
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @DeleteMapping("{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
