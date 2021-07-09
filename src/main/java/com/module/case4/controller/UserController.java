package com.module.case4.controller;


import com.module.case4.model.users.Role;
import com.module.case4.model.users.User;
import com.module.case4.model.users.UserForm;
import com.module.case4.security.appUser.AppUserService;
import com.module.case4.service.IRoleService;
import com.module.case4.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

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


    @Autowired
    public AppUserService appUserService;

//    @ModelAttribute("currentUser")
//       public User getUser(){
//        User userCurrent= appUserService.getCurrentUser();
//        System.out.println(userCurrent.toString());
//        return userCurrent;
//
//       }

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
    public ResponseEntity<User> createUser2(@ModelAttribute UserForm userForm){
        User user = userService.changeUserForm(userForm);
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);

    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        userService.delete(id);
        String message="User is deleted";
        return new ResponseEntity<>(message,HttpStatus.OK);
    }



    @PutMapping("/edit/{id}")
    public ResponseEntity<User> editUser(@ModelAttribute UserForm userForm, @PathVariable Long id){
        User user = userService.changeUserForm(userForm);
        user.setId(id);
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
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


}
