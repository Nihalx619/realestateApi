package com.realestate.realestateapi.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "agents")
public class Agent implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    // Spring Security — who is this user and what can they do?
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getUsername() {
        return email; // we use email as the login username
    }

    @Override
    public String getPassword() {
        return password;
    }

    // Spring Security — is this account usable?
    // All return true for now — in a real banking app you'd
    // implement these to lock/expire accounts
    @Override
    public boolean isAccountNonExpired() {
        return true; // account never expires
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // account never gets locked
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // password never expires
    }

    @Override
    public boolean isEnabled() {
        return true; // account is always active
    }
}