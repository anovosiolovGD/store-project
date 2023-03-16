package com.nvs.store.service;

import com.nvs.store.models.product.Product;
import com.nvs.store.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;


    @Test
    public void getAllProductsTest() {
        when(productRepository.findAll()).thenReturn(Stream.of(
                new Product(1L, "strawberry", 25, BigDecimal.valueOf(45.60)),
                new Product(1L, "banana", 31, BigDecimal.valueOf(37.90))).collect(Collectors.toList()));
        assertEquals(2, productService.getAllProducts().size());
    }

    @Test
    public void getProductTest() {
        Long id = 1L;
        Optional<Product> product = Optional.of(new Product(1L, "strawberry", 25, BigDecimal.valueOf(45.60)));
        Mockito.when(productRepository.findById(id)).thenReturn(product);
        Optional<Product> productById = productService.getProduct(id);
        assertEquals(product, productById);
    }

    @Test
    public void addProductTest() {
        Optional<Product> product = Optional.of(new Product(1L, "orange", 49, BigDecimal.valueOf(23.30)));
        when(productRepository.save(product.get())).thenReturn(product.get());
        Mockito.when(productRepository.findById(product.get().getId())).thenReturn(product);
        Optional<Product> productById = productService.getProduct(product.get().getId());
        assertEquals(product,productById);
    }

    @Test
    public void updateProductTest() {
        Optional<Product> product = Optional.of(new Product(1L, "orange", 49, BigDecimal.valueOf(23.30)));
        Long id = 1L;
        Mockito.when(productRepository.findById(id)).thenReturn(product);
        Optional<Product> updProduct=productRepository.findById(id);
        Optional<Product> product2 = Optional.of(new Product(1L, "orange", 49, BigDecimal.valueOf(21.50)));
        updProduct.get().setTitle(product2.get().getTitle());
        updProduct.get().setAvailable(product2.get().getAvailable());
        updProduct.get().setPrice(product2.get().getPrice());
        when(productRepository.save(product2.get())).thenReturn(product2.get());
        Mockito.when(productRepository.findById(updProduct.get().getId())).thenReturn(updProduct);
        assertEquals(product2, updProduct);
    }

    @Test
    public void deleteProductTest() {
        Product product = new Product(1L, "strawberry", 25, BigDecimal.valueOf(45.60));
        productService.deleteProduct(product.getId());
        verify(productRepository,times(1)).deleteById(product.getId());
    }

}
