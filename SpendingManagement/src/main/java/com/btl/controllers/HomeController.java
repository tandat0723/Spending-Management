package com.btl.controllers;

import com.btl.pojo.User;
import com.btl.service.PersonalTransactionService;
import com.btl.service.UserService;
import javax.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@ControllerAdvice
public class HomeController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("sucMsg", model.asMap().get("sucMsg"));
        return "index";
    }

    @ModelAttribute
    public void addAttributes(Model model, Authentication authentication) {
        if (authentication != null) {
            model.addAttribute("currentUser", this.userService.getByUsername(authentication.getName()));
        }
    }

    @RequestMapping("/me/view")
    public String aboutMeView(Model model, Authentication authentication) {
        User user;
        try {
            user = userService.getUserById(userService.getByUsername(authentication.getName()).getId());
            model.addAttribute("user", user);
        } catch (NoResultException ex) {
            System.out.println(ex.getMessage());
        }

        return "me-view";
    }

    @RequestMapping("/me/edit")
    public String aboutMeEditView(Model model) {
        model.addAttribute("sucMsg", model.asMap().get("sucMsg"));
        return "index";
    }

    @PostMapping("/me/edit")
    public String aboutMeEdit(Model model) {
        model.addAttribute("sucMsg", model.asMap().get("sucMsg"));
        return "index";
    }
}
