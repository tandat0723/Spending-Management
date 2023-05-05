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
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @RequestMapping("/spending-admin")
    public String addSpending(Model model, @ModelAttribute(value = "personal") @Valid PersonalTransaction personalTransaction,
            BindingResult rs, final RedirectAttributes redirectAttributes) {
        String sucMsg = null;
        try {
            if (rs.hasErrors()) {
                return "spending-admin";
            }
            if (personalTransaction.getUserId().getId() != null) {
                if (this.personalTransactionService.addOrUpdate(personalTransaction) == true) {
                    model.addAttribute("sucMsg", sucMsg);
                }

                return "redirect:/admin/spending-admin";
            } else {
                model.addAttribute("errMsg", "Lỗi! Xin liên hệ bộ phận IT để được hỗ trợ!");
            }
        } catch (Exception ex) {
            model.addAttribute("errMsg", ex);
        }
        return "spending-admin";
    }

    @GetMapping("/spending-admin/{personalId}")
    public String updateSpending(Model model, @PathVariable(value = "personalId") int id) {
        model.addAttribute("personal", this.personalTransactionService.getById(id));
        return "account-admin";
    }
}
