/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btl.controllers;

import com.btl.pojo.PersonalTransaction;
import com.btl.pojo.User;
import com.btl.service.PersonalTransactionService;
import com.btl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author phuan
 */
@RestController
@RequestMapping("/api")
public class ApiUserController {

    @Autowired
    private UserService userService;
    @Autowired
    private PersonalTransactionService personalTransactionService;

    @DeleteMapping("/users/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable(value = "userId") int id) {
        this.userService.deleteUser(id);
    }

    @GetMapping("/account-admin/view/{userId}")
    public ResponseEntity<User> aboutAccountView(@PathVariable(value = "userId") int id) {
        User user = this.userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    
    @DeleteMapping("/spending/{spendingId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSpending(@PathVariable(value = "spendingId") int id) {
        this.personalTransactionService.deleteSpending(id);
    }

    @GetMapping("/spending-admin/view/{spendingId}")
    public ResponseEntity<PersonalTransaction> aboutSpendingView(@PathVariable(value = "spendingId") int id) {
        PersonalTransaction personalTransaction = this.personalTransactionService.getById(id);
        return new ResponseEntity<>(personalTransaction, HttpStatus.OK);
    }
}
