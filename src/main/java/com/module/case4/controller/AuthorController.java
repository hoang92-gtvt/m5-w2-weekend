package com.module.case4.controller;


import com.module.case4.dto.LoginRequest;
import com.module.case4.dto.UserLogin;
import com.module.case4.model.users.Role;
import com.module.case4.model.users.User;
import com.module.case4.repository.IRoleRepository;
import com.module.case4.repository.IUserRepository;
import com.module.case4.security.appUser.AppUserService;
import com.module.case4.security.appUser.IAppUserService;
import com.module.case4.service.IRoleService;
import com.module.case4.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthorController {
    @Autowired
    AppUserService appUserService;

    @Autowired
    IUserService userService;

    @Autowired
    IRoleService roleService;



    @PostMapping("/login")
    public ResponseEntity<?> login(@ModelAttribute LoginRequest loginRequest){
        String message= "Login fail";
        Optional<User> user = userService.findByUsername(loginRequest.getUsername());

        if(user.isPresent()){

            if(user.get().getPassword().equals(loginRequest.getPassword())){
                String name = user.get().getName();
                Role roles = (Role) user.get().getRoles().toArray()[0];
                String role = roles.getName().toString();
                Long id = user.get().getId();
                String image = user.get().getAvatar();


                return new ResponseEntity<>(
                        new UserLogin(name,role, id, image),  HttpStatus.OK);
            }


        }
        return new ResponseEntity<>(message, HttpStatus.OK);



    }




}
