package com.nvs.store.repository;

import com.nvs.store.models.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAll();

    List<Product> findAllByIdIsLike(Long id);

    Product getProductById(Long id);

}
