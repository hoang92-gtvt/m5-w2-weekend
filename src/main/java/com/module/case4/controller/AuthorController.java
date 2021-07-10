package com.module.case4.controller;


import com.module.case4.dto.LoginRequest;
import com.module.case4.repository.IRoleRepository;
import com.module.case4.repository.IUserRepository;
import com.module.case4.security.appUser.AppUserService;
import com.module.case4.security.appUser.IAppUserService;
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
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthorController {
    @Autowired
    AppUserService appUserService;

    @Autowired
    IUserRepository userRepository;

    @Autowired
    IRoleRepository roleRepository;



    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Validated @RequestBody LoginRequest loginRequest, HttpServletResponse response, HttpServletRequest request){
        String username= request.getHeader("username");
        String password= request.getHeader("password");
        if(username==null||password==null){
            return new ResponseEntity<>()
        }


        else {
            return new ResponseEntity<>(new MessageResponse("xin lỗi tài khoản đã bị khoá"),HttpStatus.OK);
        }

    }
    @PostMapping("/signup")
    public ResponseEntity<?> register(@Valid @RequestBody SignupRequest signUpForm){
        if(userRepository.existsByUserName(signUpForm.getUserName())){
            return new ResponseEntity<>(new MessageResponse("The username existed! Please try again!"), HttpStatus.NOT_FOUND);
        }
        if(userRepository.existsByEmail(signUpForm.getEmail())){
            return new ResponseEntity<>(new MessageResponse("The email existed! Please try again"), HttpStatus.NOT_FOUND);
        }
        User user = new User(signUpForm.getUserName(), signUpForm.getEmail(),passwordEncoder.encode(signUpForm.getPassWord()));
        Set<String> strRoles = signUpForm.getRole();
        Set<Role> roles = new HashSet<>();
        for (String role: strRoles
        ) {
            switch (role){
                case "admin":
                    Role adminRole = roleRepository.findByName("ROLE_ADMIN").orElseThrow(
                            ()-> new RuntimeException("Role not found")
                    );
                    roles.add(adminRole);
                    break;
                default:
                    Role userRole = roleRepository.findByName("ROLE_USER").orElseThrow( ()-> new RuntimeException("Role not found"));
                    roles.add(userRole);
            }
        }
        ;
        user.setRoles(roles);
        user.setName(signUpForm.getName());
        userRepository.save(user);
        return new ResponseEntity<>(new MessageResponse("Create user success!"), HttpStatus.OK);
    }



}
