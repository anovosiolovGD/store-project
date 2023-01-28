package com.nvs.store.models;

public class UserCustomer {
    int id;
    String name;
    String email;

    public UserCustomer(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public UserCustomer() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
