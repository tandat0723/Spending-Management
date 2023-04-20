/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btl.controllers;

import com.btl.pojo.PersonalTransaction;
import com.btl.pojo.Status;
import com.btl.pojo.User;
import com.btl.service.PersonalTransactionService;
import com.btl.service.UserService;
import com.btl.validator.UserValidator;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author trant
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private PersonalTransactionService personalTransactionService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(userValidator);
    }

    @RequestMapping("/login")
    public String index(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        }
        return "redirect:/";
    }

    @GetMapping(value = "/register")
    public String RegisterView(Model model) {
        User user = new User();
        user.setId(0);
        model.addAttribute("user", user);
        model.addAttribute("errMsg", model.asMap().get("errMsg"));

        return "register";
    }

    @PostMapping(value = "/register")
    public String RegisterProcess(Model model, @ModelAttribute(value = "user") @Valid User user,
            BindingResult result, final RedirectAttributes redirectAttributes) {
        String errMsg = null;
        String sucMsg = null;
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            return "register";
        }
        
        user.setActive(new Status(1));

        boolean addOrUpdates = this.userService.addOrUpdateUser(user);
        if (addOrUpdates) {
            if (user.getUserRole().getId().equals(3)) {
                PersonalTransaction personalTransaction = new PersonalTransaction();
                personalTransaction.setId(0);

                personalTransactionService.addOrUpdate(personalTransaction);
                user.setPersonalTransactionId(personalTransaction);
                userService.addOrUpdateNoPassword(user);
            }
            sucMsg = String.format("Đăng ký tài khoản '%s' thành công", user.getUsername(),
                    user.getUserRole().getId().equals(3) ? "Người dùng" : "");

        } else {
            errMsg = String.format("Đăng ký tài khoản '%s' không thành công", user.getUsername(),
                    redirectAttributes.addFlashAttribute("errMsg", errMsg));
            return "register";
        }
        redirectAttributes.addFlashAttribute("sucMsg", sucMsg);
        return "redirect:/login";
    }

    @RequestMapping("/forgot-password")
    public String forgotPassword() {
        return "forgot-password";
    }

    @RequestMapping("/me/profile/change-password")
    public String changePassword() {
        return "change-password";
    }
}
