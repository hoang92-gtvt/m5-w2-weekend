//package com.module.case4.security.userPrincal;
//
//
//
//import com.module.case4.model.user.User;
//import com.module.case4.repository.IUserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import javax.transaction.Transactional;
//@Service
//public class UserDetailServiceLocal implements UserDetailsService{
//    @Autowired
//    IUserRepository userRepository;
//
//    @Override
//    @Transactional
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User not found -> username or password"+username));
//        return UserPrinciple.build(user);
//    }
//}
