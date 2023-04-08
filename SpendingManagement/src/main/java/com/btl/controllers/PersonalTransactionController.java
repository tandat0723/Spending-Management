/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btl.controllers;

import com.btl.service.PersonalTransactionService;
import com.btl.service.UserService;
import com.btl.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author trant
 */
@Controller
public class PersonalTransactionController {

    @Autowired
    private UserService userService;
    @Autowired
    private PersonalTransactionService personalTransactionService;
    @Autowired
    private UserValidator userValidator;

    @RequestMapping("/personal-transaction")
    public String index(Model model) {
        model.addAttribute("errMsg", model.asMap().get("errMsg"));
        model.addAttribute("sucMsg", model.asMap().get("sucMsg"));

        return "personal-transaction";
    }
}
