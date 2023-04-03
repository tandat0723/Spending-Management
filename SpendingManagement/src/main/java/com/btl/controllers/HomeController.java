package com.btl.controllers;

import com.btl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@ControllerAdvice
public class HomeController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    public void addAttributes(Model model, Authentication authentication) {
        if (authentication != null) {
            model.addAttribute("currentUser", this.userService.getByUsername(authentication.getName()));
        }

    }

}
