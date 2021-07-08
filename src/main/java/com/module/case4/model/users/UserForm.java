package com.module.case4.model.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.module.case4.model.course.Subject;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

public class UserForm {


    private Long id;

    @NotBlank
    @Size(min = 3, max = 50)
    private String name; // them vao de hien thi ten nguoi dung thay cho ten username

    @NotBlank
    @Size(min = 3, max = 50)
    private String username;

    @NotBlank
    @Email
    private String email;



    @NotBlank
    @Size(min = 6, max = 50)
    private String password;



    private MultipartFile avatar;


    private Set<Role> roles;

    @NotBlank
    private String phone;

    private String address;

    private String description;

    private String certificate;

    Set<Subject> subjects = new HashSet<>();

    public UserForm() {
    }

    public UserForm(Long id, @NotBlank @Size(min = 3, max = 50) String name, @NotBlank @Size(min = 3, max = 50) String username, @NotBlank @Email String email, @NotBlank @Size(min = 6, max = 50) String password, MultipartFile avatar, Set<Role> roles, @NotBlank String phone, String address, String description, String certificate, Set<Subject> subjects) {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public MultipartFile getAvatar() {
        return avatar;
    }

    public void setAvatar(MultipartFile avatar) {
        this.avatar = avatar;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }


}
