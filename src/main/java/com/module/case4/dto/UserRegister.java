package com.module.case4.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.module.case4.model.course.Subject;
import com.module.case4.model.users.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserRegister {


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






}
