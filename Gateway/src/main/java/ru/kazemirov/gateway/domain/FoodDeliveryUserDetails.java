package ru.kazemirov.gateway.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class FoodDeliveryUserDetails implements UserDetails {
    private String userName;
    private String password;
    private boolean active;
    private List<GrantedAuthority> authorities;

    public FoodDeliveryUserDetails(User user) {
        this.userName = user.getEmail();
        this.password = user.getPassword();
        this.active = user.isActive();
        this.authorities = new ArrayList<GrantedAuthority>();
        for (Role role: user.getRole()){
            this.authorities.add(new SimpleGrantedAuthority("ROLE_" + role.toString()));
        }
        System.out.println(this.userName);
        System.out.println(this.password);
        System.out.println(this.active);
        System.out.println(this.authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
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
        return active;
    }
}
