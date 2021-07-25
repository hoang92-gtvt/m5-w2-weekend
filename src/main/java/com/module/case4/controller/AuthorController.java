package com.module.case4.controller;


import com.module.case4.dto.UserLogin;
import com.module.case4.dto.UserRegister;
import com.module.case4.dto.UserResponse;
import com.module.case4.model.users.Role;
import com.module.case4.model.users.User;
import com.module.case4.security.jwt.JwtProvider;
import com.module.case4.security.userPrincal.UserPrinciple;
import com.module.case4.service.IRoleService;
import com.module.case4.service.IUserService;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthorController {

    @Autowired
    IUserService userService;

    @Autowired
    IRoleService roleService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;



    @PostMapping("/login")
    public ResponseEntity<?> login(@Validated @RequestBody UserLogin userLogin){

        User user = userService.findByUsername(userLogin.getUsername()).get();

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLogin.getUsername(), userLogin.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.createToken(authentication);

        UserPrinciple userDetails = (UserPrinciple) authentication.getPrincipal();

        UserResponse  userResponse = new UserResponse(
                token,
                user.getId(),
                userDetails.getName(),
                user.getAvatar(),
                userDetails.getAuthorities()
        );

        return new ResponseEntity<>(userResponse, HttpStatus.OK);


    }





}
