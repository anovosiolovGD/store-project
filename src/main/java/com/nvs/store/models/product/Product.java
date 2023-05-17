package com.nvs.store.models.product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue
    private Long id;
    @NotBlank(message = "Title is mandatory")
    private String title;
    @Min(value = 0, message = "This value should be positive")
    private Integer available;
    @Min(value = 1, message = "This value should be positive and greater than 0")
    private BigDecimal price;

}
