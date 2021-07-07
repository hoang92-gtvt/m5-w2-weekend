package com.module.case4.service;

import com.module.case4.model.user.User;
import com.module.case4.model.user.UserForm;

import java.util.List;
import java.util.Optional;

public interface IUserService  {
    Optional<User> findByUsername(String name); //Tim kiem User co ton tai trong DB khong?
    Boolean existsByUsername(String username); //username da co trong DB chua, khi tao du lieu
    Boolean existsByEmail(String email); //email da co trong DB chua
    User save(User user);

    Iterable<User> findAll();
    Optional<User> findById(Long id);
    void delete(Long id);

    User changeUserForm(UserForm userForm);



}
