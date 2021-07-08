package com.module.case4.security.appUser;

import com.module.case4.model.users.User;
import com.module.case4.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class AppUserService implements IAppUserService, UserDetailsService {
    @Autowired
    IUserRepository userRepository;


    @Override
    public User getUserByUserName(String name) {
        return userRepository.findByUsername(name).get();
    }

    @Override
    public User getCurrentUser() {
        User user;
        String userName;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        user = this.getUserByUserName(userName);
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}