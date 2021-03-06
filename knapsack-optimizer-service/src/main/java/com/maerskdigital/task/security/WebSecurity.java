package com.maerskdigital.task.security;

import org.springframework.http.HttpMethod;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static com.maerskdigital.task.security.SecurityConstants.*;

@EnableWebSecurity

public class WebSecurity extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public WebSecurity(UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;

    }

    @Override

    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
                .antMatchers(HttpMethod.GET, "/").permitAll()
                .antMatchers(HttpMethod.GET, "/**.js").permitAll()
                .antMatchers(HttpMethod.GET, "/**.css").permitAll()
                .antMatchers(HttpMethod.GET, "/**.html").permitAll()
                .antMatchers(HttpMethod.GET, "/vendor/**").permitAll()
                .antMatchers(HttpMethod.GET, "/login/**").permitAll()
                .antMatchers(HttpMethod.GET, "/home/**").permitAll()
                .antMatchers(HttpMethod.GET, "/signup/**").permitAll()      
                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()));

    }

    @Override

    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

}