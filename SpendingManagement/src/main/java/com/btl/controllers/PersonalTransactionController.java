/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btl.controllers;

import com.btl.pojo.PersonalTransaction;
import com.btl.pojo.TransactionType;
import com.btl.service.PersonalTransactionService;
import com.btl.service.TransactionTypeService;
import com.btl.service.UserService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    private TransactionTypeService transactionTypeService;

    public void commonService(Model model) {
        model.addAttribute("userService", userService);
        model.addAttribute("personalTransactionService", personalTransactionService);
    }

    @RequestMapping("/user/personal-transaction")
    public String index(Model model, Authentication authentication,
            @RequestParam(required = false) Map<String, String> params) {
        if (this.userService.getByUsername(authentication.getName()).getActive().getId() == 6) {
            return "home";
        }

        int page = Integer.parseInt(params.getOrDefault("page", "1"));
        String name = params.getOrDefault("name", null);
        String type = params.getOrDefault("transactionType", null);
        String purpose = params.getOrDefault("purpose", null);
        String description = params.getOrDefault("description", null);
        String price = params.getOrDefault("price", null);

        Map<String, String> p = new HashMap<>();
        if (name != null) {
            p.put("name", name);
            model.addAttribute("name", name);
        }
        if (type != null) {
            p.put("transactionType", type);
            model.addAttribute("transactionType", type);
        }
        if (purpose != null) {
            p.put("purpose", purpose);
            model.addAttribute("purpose", purpose);
        }
        if (description != null) {
            p.put("description", description);
            model.addAttribute("description", description);
        }
        if (price != null) {
            p.put("price", price);
            model.addAttribute("price", price);
        }

        model.addAttribute("errMsg", model.asMap().get("errMsg"));
        model.addAttribute("sucMsg", model.asMap().get("sucMsg"));

        return "personal-transaction";
    }

    @GetMapping("/user/personal-transaction/add-or-update")
    public String addOrUpdate(Model model, Authentication authentication,
            @RequestParam(name = "id", defaultValue = "0") int id) {
        if (this.userService.getByUsername(authentication.getName()).getActive().getId() == 6) {
            return "home";
        }
        int userId = this.userService.getByUsername(authentication.getName()).getId();
        if (id > 0) {
            PersonalTransaction personalTransaction = this.personalTransactionService.getById(id);
            if (personalTransaction != null && personalTransaction.getTransactionType().getId() == userId) {
                model.addAttribute("personalTransaction", personalTransaction);
            } else {
                return "redirect:/access-denied";
            }
        } else {
            PersonalTransaction personalTransaction = new PersonalTransaction();
            personalTransaction.setId(0);
            model.addAttribute("personalTransaction", personalTransaction);
        }

        return "personal-transaction-add";
    }

    @GetMapping("/user/personal-transaction-view")
    public String PersonalTransactionView(Model model, Authentication authentication,
            @RequestParam(name = "id", defaultValue = "0") int id) {
        if (this.userService.getByUsername(authentication.getName()).getActive().getId() == 6) {
            return "home";
        }
        
        int userId = this.userService.getByUsername(authentication.getName()).getId();
        
        if (id > 0) {
            PersonalTransaction personalTransaction = this.personalTransactionService.getById(id);
            if (personalTransaction != null && personalTransaction.getUserId().getId() == userId) {
                model.addAttribute("personalTransaction", personalTransaction);

                TransactionType transactionType = this.transactionTypeService.getById(personalTransaction.getTransactionType().getId());
                model.addAttribute("transactionType", transactionType);

            } else {
                return "redirect:/access-denied";
            }
        } else {
            return "personal-transaction-view";
        }
        model.addAttribute("errMsg", model.asMap().get("errMsg"));

        return "personal-transaction-view";

    }
}
