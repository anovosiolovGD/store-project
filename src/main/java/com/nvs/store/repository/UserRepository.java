package com.nvs.store.repository;

import com.nvs.store.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String name);
    boolean existsByUsername (String username);
}
