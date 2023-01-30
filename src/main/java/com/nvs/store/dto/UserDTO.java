package com.nvs.store.dto;

import com.nvs.store.models.User;
import lombok.Builder;
import lombok.Data;

import static org.springframework.security.oauth2.jwt.JwtClaimsSet.builder;

@Builder
@Data
public class UserDTO {
    private String id;
    private String username;

    public static UserDTO from(User user) {
        return builder()
                .id(String.valueOf(user.getId()))
                .username(user.getUsername())
                .build();
    }
}
