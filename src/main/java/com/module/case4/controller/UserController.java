package com.module.case4.controller;


import com.module.case4.model.user.User;
import com.module.case4.model.user.UserForm;
import com.module.case4.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    public IUserService userService;

//    @ModelAttribute("currentAdmin")
//    public  User getUser(){
//        return userService.
//    }

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
//    public ResponseEntity<User> createUser(@RequestBody UserForm  userForm){
//        User user
//    }

}
