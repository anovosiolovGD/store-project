package com.nvs.store.service;

import com.nvs.store.models.product.Product;
import com.nvs.store.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Product getLastProduct() {
        return productRepository.findTopByOrderByIdDesc();
    }


    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return ResponseEntity.status(OK).body(products);
    }

    public ResponseEntity<Product> getProduct(Long id) {
        if (productRepository.getProductById(id) == null) {
            return ResponseEntity.status(NOT_FOUND).body(null);
        }
        return ResponseEntity.status(OK).body(productRepository.getProductById(id));
    }

    public ResponseEntity<Product> addProduct(Product product) {
        productRepository.save(product);
        return ResponseEntity.status(CREATED).body(getLastProduct());
    }

    public ResponseEntity<Product> updateProduct() {
        return null;
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }


}
