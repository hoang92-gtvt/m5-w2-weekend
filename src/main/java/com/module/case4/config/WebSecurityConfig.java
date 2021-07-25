package com.module.case4.config;


import com.module.case4.model.users.Role;
import com.module.case4.model.users.User;
import com.module.case4.security.jwt.JwtEntryPoint;
import com.module.case4.security.jwt.JwtTokenFilter;
import com.module.case4.security.userPrincal.UserDetailServiceImp;
import com.module.case4.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailServiceImp userDetailService;

    @Autowired
    private JwtEntryPoint jwtEntryPoint;
    @Bean
    public JwtTokenFilter jwtTokenFilter(){
        return new JwtTokenFilter();
    }
    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailService)
                .passwordEncoder(passwordEncoder());
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors().and().csrf().disable().
                authorizeRequests()
                .antMatchers("/api/user/**").permitAll()
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers("/api/detail/**").permitAll()
                .antMatchers("/api/course/**").hasAuthority("ADMIN")
                .antMatchers("/api/subject/**").permitAll()
                .antMatchers("/api/district/**").permitAll()
                .anyRequest().authenticated()
                .and().exceptionHandling()
                .authenticationEntryPoint(jwtEntryPoint)
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }


    @Autowired
    IUserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        List<User> users = (List<User>) userService.findAll();

        if (users.isEmpty()) {
            User admin = new User();
            Set<Role> roles = new HashSet<>();
            roles.add(new Role(1L, "ADMIN"));
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("123456"));

            admin.setRoles(roles);
            userService.save(admin);
        }
    }

}
