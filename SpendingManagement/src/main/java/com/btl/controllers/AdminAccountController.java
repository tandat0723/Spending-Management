/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btl.controllers;

import com.btl.pojo.PersonalTransaction;
import com.btl.pojo.User;
import com.btl.pojo.Status;
import com.btl.pojo.UserRole;
import com.btl.service.PersonalTransactionService;
import com.btl.service.StatusService;
import com.btl.service.UserRoleService;
import com.btl.service.UserService;
import java.util.List;
import java.util.Set;
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
public class AdminAccountController {

    @Autowired
    private UserService userService;
    @Autowired
    private StatusService statusService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private PersonalTransactionService personalTransactionService;

    @ModelAttribute
    public void commonAttributes(Model model) {
        model.addAttribute("users", this.userService.getAllUsers(null));
        List<Status> status = this.statusService.getStatus();
        model.addAttribute("status", status);
        List<UserRole> roles = this.userRoleService.getUserRole();
        model.addAttribute("roles", roles);
    }

    @RequestMapping("/account-admin")
    public String addUser(Model model, @ModelAttribute(value = "user") @Valid User user,
            BindingResult rs, final RedirectAttributes redirectAttributes) {
        String sucMsg = null;
        try {
            if (rs.hasErrors()) {
                return "account-admin";
            }

            if (this.userService.addOrUpdateUser(user) == true) {
                if (user.getUserRole().getId().equals(3)) {
                    PersonalTransaction personalTransaction = new PersonalTransaction();
                    personalTransaction.setId(0);

                    personalTransactionService.addOrUpdate(personalTransaction);
                    user.setPersonalTransactionSet((Set<PersonalTransaction>) personalTransaction);
                    userService.addOrUpdateNoPassword(user);
                }
                sucMsg = String.format("Thêm tài khoản '%s' thành công", user.getUsername(),
                        user.getUserRole().getId().equals(3) ? "Người dùng" : "");
                model.addAttribute("sucMsg", sucMsg);
                return "redirect:/admin/account-admin";
            } else {
                model.addAttribute("errMsg", "Lỗi! Xin liên hệ bộ phận IT để được hỗ trợ!");
            }
        } catch (Exception ex) {
            model.addAttribute("errMsg", ex);
        }
        return "account-admin";
    }

    @GetMapping("/account-admin")
    public String users(Model model) {
        User user = new User();
        user.setId(0);
        model.addAttribute("user", user);

        return "account-admin";
    }

    @GetMapping("/account-admin/{userId}")
    public String updateUser(Model model, @PathVariable(value = "userId") int id) {
        model.addAttribute("user", this.userService.getUserById(id));
        return "account-admin";
    }

    @GetMapping("/account-admin/view/{userId}")
    public String aboutAccountView(Model model, @PathVariable(value = "userId") int id) {
        try {
            model.addAttribute("userDetail", this.userService.getUserById(id));
        } catch (NoResultException ex) {
            System.out.println(ex.getMessage());
        }

        return "account-view";
    }
}
