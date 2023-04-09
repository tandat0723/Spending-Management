/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btl.controllers;

import com.btl.service.UserService;
import com.btl.pojo.User;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author phuan
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    
    @Autowired
    private UserService userService;
    
    @ModelAttribute
    public void commonAttributes(Model model) {
        model.addAttribute("users", this.userService.getUsers(null, 0));
    }
    
    @RequestMapping("/users")
    public String addUser(Model model, 
            @ModelAttribute(value = "user") @Valid User u,
            BindingResult rs) {
        if (rs.hasErrors())
            return "users";
        
        if (this.userService.addOrUpdateUser(u) == true)
            return "redirect:/admin/users";
        else
            model.addAttribute("errMsg", "Lỗi! Xin liên hệ người quản trị xử lý!!!");
        
        return "users";
    }
    
    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("user", new User());
        
        return "users";
    }
    
    @GetMapping("/users/{userId}")
    public String updateUser(Model model, @PathVariable(value = "userId") int id) {
        model.addAttribute("user", this.userService.getUserById(id));
        return "users";
    }
}
