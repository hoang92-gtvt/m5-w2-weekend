package com.module.case4.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.module.case4.model.course.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
//@Table(name="app_users", uniqueConstraints = {
//        @UniqueConstraint(columnNames = "username"),
//        @UniqueConstraint(columnNames = "email")
//})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 50)
    private String name; // them vao de hien thi ten nguoi dung thay cho ten username

    @NotBlank
    @Size(min = 3, max = 50)
    private String username;

    @NaturalId
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;


    @JsonIgnore //khong truyen du lieu ra ngoai
    @NotBlank
    @Size(min = 6, max = 50)
    private String password;


    @Lob // khai báo ký tự rất dài
    private String avatar;

    @ManyToOne
    private Role role;

    @NotBlank
    private String phone;

    private String address;

    private String description;


    private String certificate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="user_subject",
               joinColumns = @JoinColumn(name="user_id"),
               inverseJoinColumns = @JoinColumn(name="subject_id"))
    Set<Subject> monhocs = new HashSet<>();



}