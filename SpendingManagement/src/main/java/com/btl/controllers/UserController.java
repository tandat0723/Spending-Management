/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btl.controllers;

import com.btl.pojo.User;
import com.btl.service.UserService;
import javax.persistence.NoResultException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author trant
 */
@Controller
public class UserController {
    private UserService UserService;
    @RequestMapping("/user")
    public String index(Model model) {
        model.addAttribute("errMsg", model.asMap().get("errMsg"));
        model.addAttribute("sucMsg", model.asMap().get("sucMsg"));
        return "user";
    }
    
    @GetMapping("/user/user-info/add-or-update")
    public String updateUserView(Model model, Authentication authentication){
        int id = this.UserService.getByUsername(authentication.getName()).getId();
        User user;
        try {
            user = UserService.getUserById(id);
        } catch (NoResultException e) {
            user = new User();
            user.setId(0);
        }
        
        model.addAttribute("user", user);
        model.addAttribute("id", id);
        model.addAttribute("actionUrl", "/user/user-info/add-or-update");
        return "admin-add-user";
    }
}
