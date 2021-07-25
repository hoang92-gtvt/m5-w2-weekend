package com.module.case4.service.impl;

import com.module.case4.dto.UserRegister;
import com.module.case4.model.users.Role;
import com.module.case4.model.users.User;
import com.module.case4.repository.IUserRepository;
import com.module.case4.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Optional;
@Service
public class UserService implements IUserService {

    @Autowired
    IUserRepository userRepository;

    @Autowired
    Environment environment;

    @Autowired
    PasswordEncoder passwordEncoder;

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
    @Transactional
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
    public User changeUserForm(UserRegister userRegister) {

        User user = new User();
        user.setId(userRegister.getId());
        user.setName(userRegister.getName());
        user.setUsername(userRegister.getUsername());
        user.setEmail(userRegister.getEmail());

        String pass = userRegister.getPassword();
        user.setPassword(passwordEncoder.encode(pass));

        user.setPhone(userRegister.getPhone());
        user.setRoles(userRegister.getRoles());


        user.setAddress(userRegister.getAddress());
        user.setDescription(userRegister.getDescription());
        user.setCertificate(userRegister.getCertificate());
        user.setSubjects(userRegister.getSubjects());

        MultipartFile multipartFile = userRegister.getAvatar();
        if(multipartFile!=null) {
            String fileName = multipartFile.getOriginalFilename();
            String fileUpload = environment.getProperty("upload.path").toString();
            try {
                FileCopyUtils.copy(userRegister.getAvatar().getBytes(), new File(fileUpload + fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }

            user.setAvatar(fileName);
        }

        return user;


    }

    @Override
    public Iterable<User> findByRoles(Role role) {
        return userRepository.findByRoles(role);
    }

    @Override
    public Iterable<User> findByRoles(Long role_id) {
        return userRepository.findByRoles(role_id);

    }


}
