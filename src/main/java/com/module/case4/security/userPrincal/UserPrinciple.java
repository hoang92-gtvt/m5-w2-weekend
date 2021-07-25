package com.module.case4.security.userPrincal;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.module.case4.model.course.Subject;
import com.module.case4.model.users.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserPrinciple implements UserDetails {
    private Long id;
    private String name;
    private String username;
    private String email;

    @JsonIgnore
    private String password;

    private String avatar;

    private Collection<? extends GrantedAuthority> roles ;

    private String phone;

    private String address;

    private String description;


    private String certificate;

    Set<Subject> subjects = new HashSet<>();

    public UserPrinciple() {
    }

    public UserPrinciple(Long id, String name, String username, String email, String password, String avatar, Collection<? extends GrantedAuthority> roles, String phone, String address, String description, String certificate, Set<Subject> subjects) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.avatar = avatar;
        this.roles = roles;
        this.phone = phone;
        this.address = address;
        this.description = description;
        this.certificate = certificate;
        this.subjects = subjects;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public static UserPrinciple build(User user){
        List<GrantedAuthority> authorities = user.getRoles().stream().map(
                role->
                new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
        return new UserPrinciple(
                user.getId(),
                user.getName(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getAvatar(),
                authorities,
                user.getPhone(),
                user.getAddress(),
                user.getDescription(),
                user.getCertificate(),
                user.getSubjects()

        );
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
