package com.module.case4.model.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.module.case4.model.course.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="app_users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @NotBlank
//    @Size(min = 3, max = 50)
    private String name; // them vao de hien thi ten nguoi dung thay cho ten username

//    @NotBlank
//    @Size(min = 3, max = 50)
    private String username;


    private String email;


//    @JsonIgnore //khong truyen du lieu ra ngoai
    private String password;

    private String avatar;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="user_role",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="role_id"))
    private Set<Role> roles;


    private String phone;

    private String address;

    private String description;


    private String certificate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="user_subject",
               joinColumns = @JoinColumn(name="user_id"),
               inverseJoinColumns = @JoinColumn(name="subject_id"))
    Set<Subject> subjects = new HashSet<>();

    public User(String name, String username, String email, String password, Set<Role> roles) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
}