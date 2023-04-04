/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btl.controllers;

import com.btl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

/**
 *
 * @author trant
 */
@Controller
public class LoginController {

//    @Autowired
//    private UserService userService;

    public String index() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
//            return "login";
//        }
//        return "redirect:/";
        return "login";
    }
}
