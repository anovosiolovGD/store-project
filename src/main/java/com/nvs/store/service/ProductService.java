package com.nvs.store.service;

import com.nvs.store.models.product.Product;
import com.nvs.store.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

    // TODO: 09.02.2023 is save correct method for UPDATing?  how to - request json without id field?
    public ResponseEntity<Product> updateProduct(Long id,Product product){
        Product repoProduct = productRepository.getProductById(id);
        if(Objects.equals(repoProduct,product)){
            return ResponseEntity.status(BAD_REQUEST).body(product);
        }
        repoProduct.setPrice(product.getPrice());
        repoProduct.setTitle(product.getTitle());
        repoProduct.setAvailable(product.getAvailable());
        productRepository.save(repoProduct);

        return ResponseEntity.status(OK).body(repoProduct);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }


}
