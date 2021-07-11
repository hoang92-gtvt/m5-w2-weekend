package com.module.case4.dto;

public class UserLogin {
    private String name;
    private String role;
    private Long id;
    private String image;

    public UserLogin(String name, String role, Long id, String image) {
        this.name = name;
        this.role = role;
        this.id = id;
        this.image = image;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
