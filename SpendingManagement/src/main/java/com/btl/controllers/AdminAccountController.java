/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btl.controllers;

import com.btl.pojo.User;
import com.btl.pojo.Status;
import com.btl.pojo.UserRole;
import com.btl.service.StatusService;
import com.btl.service.UserRoleService;
import com.btl.service.UserService;
import java.util.List;
import javax.servlet.http.HttpSession;
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
public class AdminAccountController {
    
    @Autowired
    private UserService userService;
    @Autowired
    private StatusService statusService;
    @Autowired
    private UserRoleService userRoleService;
    
    @ModelAttribute
    public void commonAttributes(Model model) {
        model.addAttribute("users", this.userService.getAllUsers(null));
        List<Status> status = this.statusService.getStatus();
        model.addAttribute("status", status);
        List<UserRole> roles = this.userRoleService.getUserRole();
        model.addAttribute("roles", roles);
    }
    
    @RequestMapping("/account-admin")
    public String addUser(Model model, 
            @ModelAttribute(value = "user") @Valid User u,
            BindingResult rs) {
        if (rs.hasErrors())
            return "account-admin";
        
        if (this.userService.addOrUpdateAccountUser(u) == true)
            return "redirect:/admin/account-admin";
        else
            model.addAttribute("errMsg", "Lỗi! Xin liên hệ người quản trị xử lý!!!");
        
        return "account-admin";
    }
    
    @GetMapping("/account-admin")
    public String users(Model model) {
        model.addAttribute("user", new User());
        
        return "account-admin";
    }
    
    @GetMapping("/account-admin/{userId}")
    public String updateUser(Model model, @PathVariable(value = "userId") int id) {
        model.addAttribute("user", this.userService.getUserById(id));
        return "account-admin";
    }
    @GetMapping("/spending-admin")
    public String spending(Model model) {
        return "spending-admin";
    }
}
