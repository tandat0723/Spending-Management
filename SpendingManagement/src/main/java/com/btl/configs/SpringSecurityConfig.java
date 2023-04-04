///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.btl.configs;
//
//import com.cloudinary.Cloudinary;
//import com.cloudinary.utils.ObjectUtils;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
///**
// *
// * @author trant
// */
//@Configuration
//@EnableWebSecurity
//@EnableTransactionManagement
//@ComponentScan(basePackages = {
//    "com.btl.service",
//    "com.btl.repository",
//    "com.btl.controllers",
//    "com.btl.handlers",
//    "com.btl.validator"
//})
//public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public DefaultAuthenticationEventPublisher defaultAuthenticationEventPublisher() {
//        return new DefaultAuthenticationEventPublisher();
//    }
//
//}
