package com.nvs.store.controllers;

import com.nvs.store.models.product.Product;
import com.nvs.store.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/api/v1/auth/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {

        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        if (productService.get(id) == null) {
            return ResponseEntity.status(NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(productService.get(id));
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<List<Product>> get(@PathVariable Long id) {
        return null;
    }


    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product added = productService.addProduct(product);
        return ResponseEntity.status(CREATED).body(added);
    }

    //    @PutMapping("{id}")
//    public Map<String, String> updateProduct(@PathVariable String id, @RequestBody Map<String, String> product) throws Exception {
//        Map<String, String> productFromDB = getProduct(id);
//
//        productFromDB.putAll(product);
//        productFromDB.put("id", id);
//        return productFromDB;
//    }
//
    @DeleteMapping("{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.delete(id);
    }
}
