package com.module.case4.controller;


import com.module.case4.dto.UserRegister;
import com.module.case4.model.users.Role;
import com.module.case4.model.users.User;
import com.module.case4.service.IRoleService;
import com.module.case4.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")

@RequestMapping("/api/user")
public class UserController {
    @Autowired
    public IUserService userService;

    @Autowired
    public IRoleService roleService;

    @Autowired
    Environment environment;

    @ModelAttribute("roles")
    public List<Role> findAllRole(){
        List<Role> roles = (List<Role>) roleService.getAll();
        for (int i = 0; i < roles.size() ; i++) {
            System.out.println("i = "+i);
            System.out.println("role = "+roles.get(i));
        }
        System.out.println("role.length"+roles.size());
        return roles;
    }



    @GetMapping("/admin/findAll")
    public ResponseEntity<List<User>> findAllUser(){

        List<User> users = (List<User>) userService.findAll();
        if(users.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @GetMapping("/admin/findOne/{id}")
    public ResponseEntity<User> findOneUser(@PathVariable Long id){
        Optional<User> user = userService.findById(id);
        if(!user.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }


//    @PostMapping("/create")
//    public ResponseEntity<User> createUser(@RequestBody UserForm userForm){
//        User user = userService.changeUserForm(userForm);
//        userService.save(user);
//        return new ResponseEntity<>(user, HttpStatus.CREATED);
//
//    }

    @PostMapping("/create")
    public ResponseEntity<String> createUser2(@ModelAttribute UserRegister userRegister){
        User user1 = userService.changeUserForm(userRegister);
        userService.save(user1);
        String message= "Add User Complete" ;
        return new ResponseEntity<>(message, HttpStatus.CREATED);

    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        userService.delete(id);
        String message="User is deleted";
        return new ResponseEntity<>(message,HttpStatus.OK);
    }



    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editUser(@ModelAttribute UserRegister userRegister, @PathVariable Long id){
        User user = userService.changeUserForm(userRegister);
        user.setId(id);
        userService.save(user);
        String message = "Update compelete";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PutMapping("/edit/{id}/image")
    public ResponseEntity<String> editImageUser(@RequestBody MultipartFile multipartFile, @PathVariable Long id){
        String fileName = multipartFile.getOriginalFilename();
        String fileUpload = environment.getProperty("upload.path").toString();
        try {
            FileCopyUtils.copy(multipartFile.getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        User user = userService.findById(id).get();
        user.setAvatar(fileName);
        userService.save(user);
        String url= fileUpload + fileName;
        return new ResponseEntity<>(url, HttpStatus.OK);
    }

    @GetMapping("/findByUserName/{userName}")
    public ResponseEntity<User> findUserByUserName(@PathVariable String userName){
        Optional<User> user = userService.findByUsername(userName);
        if (!user.isPresent()){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }

    @GetMapping("/findByRole/{id}")
    public ResponseEntity<List<User>> findUserByRole(@PathVariable Long id){
        Optional<Role> role = roleService.getByID(id);
        if(role.isPresent()) {
            List<User> users = (List<User>) userService.findByRoles(role.get());
            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



}
