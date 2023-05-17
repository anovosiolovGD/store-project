package com.nvs.store.controllers;

import com.nvs.store.exceptions.ProductNotExistsException;
import com.nvs.store.models.product.Product;
import com.nvs.store.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;


@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) throws ProductNotExistsException {
        if (productService.getProduct(id).isEmpty()) {
            throw new ProductNotExistsException(id);
        }
        return ResponseEntity.ok(productService.getProduct(id).get());
    }


    @PostMapping
    @ResponseStatus(CREATED)
    public Product addProduct(@RequestBody @Valid Product product) {
        return productService.addProduct(product);
    }

    @PutMapping("/{id}")
    @ResponseStatus(OK)
    public Product updateProduct(@PathVariable Long id, @RequestBody @Valid Product product){
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id) throws ProductNotExistsException {
        if (productService.getProduct(id).isEmpty()) {
            throw new ProductNotExistsException(id);
        }
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

}
