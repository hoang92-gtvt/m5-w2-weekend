package com.module.case4.controller;


import com.module.case4.model.users.Role;
import com.module.case4.model.users.User;
import com.module.case4.model.users.UserForm;
import com.module.case4.security.appUser.AppUserService;
import com.module.case4.service.IRoleService;
import com.module.case4.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")

@RequestMapping("/api/user")
public class UserController {
    @Autowired
    public IUserService userService;

    @Autowired
    public IRoleService roleService;

    @Autowired
    public AppUserService appUserService;
//
//    @ModelAttribute("currentUser")
//       public User getUser(){
//        return appUserService.getCurrentUser();
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
    @PostMapping("/create2")
    public void createUser(@ModelAttribute UserForm userForm){
        User user = userService.changeUserForm(userForm);
        User user1= userService.save(user);

    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser2(@RequestBody UserForm userForm){
        User user = userService.changeUserForm(userForm);
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);

    }
    @GetMapping("/create")
    public ModelAndView formCreate(){
        ModelAndView mav =new ModelAndView("/form");
        mav.addObject("userForm", new UserForm());

        return mav;

    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        userService.delete(id);
        String message="User is deleted";
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

}
