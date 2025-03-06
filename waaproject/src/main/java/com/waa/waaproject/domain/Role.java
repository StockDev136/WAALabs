package com.waa.waaproject.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String role;

    public Role() {
    }
    public Role(Long id, String role) {
        this.id = id;
        this.role = role;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {}
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
}
