package com.nvs.store.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ProductDTO {

    private Long id;
    private String title;
    private Integer available;
    private BigDecimal price;
}
