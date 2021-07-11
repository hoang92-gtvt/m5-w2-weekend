package com.module.case4.dto;

public class UserLogin {
    private String name;
    private String role;
    private Long id;

    public UserLogin(String name, String role, Long id) {
        this.name = name;
        this.role = role;
        this.id = id;
    }

    public UserLogin() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
