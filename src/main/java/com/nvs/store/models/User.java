package com.nvs.store.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.lang.annotation.Documented;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "Users")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity implements UserDetails {
    @NonNull
    @Column(name = "username")
    private String username;
    @NonNull
    @Column(name = "firstname")
    private String firstName;
    @NonNull
    @Column(name = "lastname")
    private String lastName;
    @NonNull
    @Column(name = "email")
    private String email;
    @NonNull
    @Column(name = "password")
    private String password;
    @Column()
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
            private List<Role>roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.EMPTY_LIST;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
