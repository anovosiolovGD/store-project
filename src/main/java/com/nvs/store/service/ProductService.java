package com.nvs.store.service;

import com.nvs.store.models.product.Product;
import com.nvs.store.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product get(Long id) {
        return productRepository.getProductById(id);
    }

    public Product addProduct(Product product) {
        productRepository.save(product);
        return product;
    }

    public void delete(Long id) {
        productRepository.deleteById(id.intValue());
    }



}
