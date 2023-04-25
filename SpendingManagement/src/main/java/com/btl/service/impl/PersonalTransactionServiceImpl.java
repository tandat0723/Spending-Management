/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btl.service.impl;

import com.btl.pojo.PersonalTransaction;
import com.btl.pojo.TransactionType;
import com.btl.repository.PersonalTransactionRepository;
import com.btl.service.PersonalTransactionService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author trant
 */
@Service
public class PersonalTransactionServiceImpl implements PersonalTransactionService {

    @Autowired
    private PersonalTransactionRepository personalTransaction;

    @Override
    public PersonalTransaction getById(int id) {
        return this.personalTransaction.getById(id);
    }

    @Override
    @Transactional
    public boolean addOrUpdate(PersonalTransaction personalTransaction) {
        return this.personalTransaction.addOrUpdate(personalTransaction);
    }

    @Override
    public PersonalTransaction getByUserId(int id) {
        return this.personalTransaction.getByUserId(id);
    }

    @Override
    public List<PersonalTransaction> getAllPersonalTransaction(Map<String, String> params) {
        return this.personalTransaction.getAllPersonalTransaction(params);
    }

    @Override
    public List<TransactionType> getAllTransactionType(Map<String, String> params) {
        return this.personalTransaction.getAllTransactionType(params);
    }

    @Override
    public int getMaxItemsInPage() {
        return this.personalTransaction.getMaxItemsInPage();
    }

}
