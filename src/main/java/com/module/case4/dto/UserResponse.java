package com.module.case4.dto;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Set;

public class UserResponse {
    private String token;
    private Long id;
    private String name;
    private String image;
    private String type = "Bearer";
    private Collection<? extends GrantedAuthority> roles;

    public UserResponse() {
    }

    public UserResponse(String token, Long id, String name, String image, Collection<? extends GrantedAuthority> roles) {
        this.token = token;
        this.id = id;
        this.name = name;
        this.image = image;
        this.roles = roles;
    }

    public Collection<? extends GrantedAuthority> getRoles() {
        return roles;
    }

    public void setRoles(Collection<? extends GrantedAuthority> roles) {
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
