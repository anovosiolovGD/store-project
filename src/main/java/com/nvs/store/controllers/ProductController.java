package com.nvs.store.controllers;

import com.nvs.store.exceptions.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("products")
public class ProductController {
    private int counter = 1;
    private final List<Map<String, String>> products = new ArrayList<Map<String, String>>() {{
        add(new TreeMap<String, String>() {{
            put("id", String.valueOf(counter++));
            put("name", "iPhone XS");
        }});
        add(new TreeMap<String, String>() {{
            put("id", String.valueOf(counter++));
            put("name", "iPhone 13");
        }});
        add(new TreeMap<String, String>() {{
            put("id", String.valueOf(counter++));
            put("name", "iPhone 6S Plus");
        }});
    }};

    @GetMapping
    public List<Map<String, String>> allProducts() {
        return products;
    }

    @GetMapping("{id}")
    public Map<String, String> getProduct(@PathVariable String id) {
        return findById(id);
    }

    private Map<String, String> findById(String id) {
        return products.stream()
                .filter(product -> product.get("id").equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public Map<String, String> addProduct(@RequestBody Map<String, String> product) {
        product.put("id", String.valueOf(counter++));
        products.add(product);
        return product;
    }
    @PutMapping ("{id}")
    public Map<String,String> updateProduct(@PathVariable String id,@RequestBody Map<String,String> product){
        Map<String,String> productFromDB = getProduct(id);

        productFromDB.putAll(product);
        productFromDB.put("id",id);
        return productFromDB;
    }
    @DeleteMapping("{id}")
    public void deleteProduct(@PathVariable String id){
        Map<String,String> product = getProduct(id);
        products.remove(product);
    }
}
