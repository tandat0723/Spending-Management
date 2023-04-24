/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btl.controllers;

import com.btl.pojo.PersonalTransaction;
import com.btl.pojo.User;
import com.btl.service.PersonalTransactionService;
import com.btl.service.UserService;
import java.util.List;
import javax.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
public class AdminSpendingController {
    
    @Autowired
    private UserService userService;
    @Autowired
    private PersonalTransactionService personalTransactionService;
    
    @ModelAttribute
    public void commonAttributes(Model model) {
        model.addAttribute("personalTransaction", this.personalTransactionService.getAllPersonalTransaction(null));
        model.addAttribute("transactionTypes", this.personalTransactionService.getAllTransactionType(null));
        List<User> userId = this.userService.getAllUsers(null);
        model.addAttribute("userId", userId);
    }
    
    @GetMapping("/spending-admin")
    public String spending(Model model) {
        PersonalTransaction personalTransaction = new PersonalTransaction();
        personalTransaction.setId(0);
        model.addAttribute("personal", personalTransaction);
        return "spending-admin";
    }
    
    @GetMapping("/spending-admin/{personalId}")
    public String updateSpending(Model model, @PathVariable(value = "personalId") int id) {
        model.addAttribute("personal", this.personalTransactionService.getById(id));
        return "account-admin";
    }

    @GetMapping("/spending-admin/view/{personalId}")
    public String aboutSpendingView(Model model, @PathVariable(value = "personalId") int id) {
        try {
            model.addAttribute("personalDetail", this.personalTransactionService.getById(id));
        } catch (NoResultException ex) {
            System.out.println(ex.getMessage());
        }

        return "spending-view";
    }
}
