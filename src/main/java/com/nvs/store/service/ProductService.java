package com.nvs.store.service;

import com.nvs.store.exceptions.ProductNotExistsException;
import com.nvs.store.models.product.Product;
import com.nvs.store.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProduct(Long id) {
        return productRepository.findById(id);
    }

    public Product addProduct(Product product) {
        productRepository.save(product);
        return productRepository.getProductById(product.getId());
    }

    public Product updateProduct(Long id, Product product) {
        Product updProduct = productRepository.getProductById(id);
        updProduct.setTitle(product.getTitle());
        updProduct.setAvailable(product.getAvailable());
        updProduct.setPrice(product.getPrice());
        productRepository.save(updProduct);
        return productRepository.getProductById(id);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
    public Product findById(Long productId) throws ProductNotExistsException {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotExistsException("product id is invalid: " + productId));
        return product;
    }

}
