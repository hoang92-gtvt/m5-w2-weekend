package com.module.case4.service.impl;

import com.module.case4.model.user.User;
import com.module.case4.model.user.UserForm;
import com.module.case4.repository.IUserRepository;
import com.module.case4.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
@Service
public class UserService implements IUserService {

    @Autowired
    IUserRepository userRepository;

    @Autowired
    Environment environment;

    @Override
    public Optional<User> findByUsername(String name) {
        return userRepository.findByUsername(name);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
         userRepository.deleteById(id);

    }

    @Override
    public User changeUserForm(UserForm userForm) {

        User user = new User();
        user.setId(userForm.getId());
        user.setName(userForm.getName());
        user.setUsername(userForm.getUsername());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());

        user.setPhone(userForm.getPhone());
        user.setRoles(userForm.getRoles());


        user.setAddress(userForm.getAddress());
        user.setDescription(userForm.getDescription());
        user.setCertificate(userForm.getCertificate());
        user.setSubjects(userForm.getSubjects());

        MultipartFile multipartFile = userForm.getAvatar();
        String fileName = multipartFile.getOriginalFilename();
        String fileUpload = environment.getProperty("upload.path").toString();
        try {
            FileCopyUtils.copy(userForm.getAvatar().getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }


        return user;


    }


}
