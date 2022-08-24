package ru.kazemirov.gateway.domain;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import java.util.Set;

@EntityScan
@Table( name = "usr")
public class User {
    @Id
    private Long id;
    private String username;
    private String password;
    private boolean active;

    @ElementCollection (targetClass = Role.class, fetch = FetchType.EAGER)
    //@CollectionTable (name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> role;

    public User() {
    }

    public User(Long id, String username, String password, boolean active, Set<Role> role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.active = active;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }
}
