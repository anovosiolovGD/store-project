package com.nvs.store.models.cart;

import com.nvs.store.dto.cart.CartItem;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long cartId;


    private Date createdDate;

    @OneToMany
    private List<CartItem> cartItems;

    public Cart() {
    }

    public Long getId() {
        return cartId;
    }

    public void setId(Long id) {
        this.cartId = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

}


