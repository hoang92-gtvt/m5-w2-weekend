package com.module.case4.config;


import com.module.case4.security.appUser.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@EnableWebSecurity
public class AppSecConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AppUserService appUserservice;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService((UserDetailsService) appUserservice).passwordEncoder(
                NoOpPasswordEncoder.getInstance()
        );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/api/user/**").permitAll()
                .and()
                .authorizeRequests().antMatchers("/api/detail/**").permitAll()
                .and()
                .authorizeRequests().antMatchers("/api/course/**").permitAll()
                .and()
                .authorizeRequests().antMatchers("/api/subject/**").permitAll()
                .and()
//                .authorizeRequests().antMatchers("/api/district/**").permitAll()
//                .and()

                .authorizeRequests().antMatchers("/api/district/**").permitAll()
                .and()

                .formLogin()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
        http.csrf().disable();
    }

}