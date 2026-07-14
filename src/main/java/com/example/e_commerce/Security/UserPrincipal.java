package com.example.e_commerce.Security;

import com.example.e_commerce.Model.Users;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class UserPrincipal implements UserDetails {
    private final Users users;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(users.getRole().getName()));
    }

    @Override
    public @Nullable String getPassword() {
        return users.getPassword();
    }

    @Override
    public String getUsername() {
        return users.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return users.getAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return users.getAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return users.getCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return users.getIsActive();
    }
}
