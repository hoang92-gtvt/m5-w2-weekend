package com.module.case4.controller;


import com.module.case4.model.user.User;
import com.module.case4.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    public IUserService userService;

//    @ModelAttribute("currentAdmin")
//    public  User getUser(){
//        return userService.
//    }

//    @GetMapping("/findAnd")
//    public ResponseEntity<User> findAll(){
//
//    }


}
