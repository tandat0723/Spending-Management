/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btl.service.impl;

import com.btl.pojo.PersonalTransaction;
import com.btl.repository.PersonalTransactionRepository;
import com.btl.service.PersonalTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author trant
 */
@Service
public class PersonalTransactionServiceImpl implements PersonalTransactionService {

    @Autowired
    private PersonalTransactionRepository PersonalTransaction;

    @Override
    public PersonalTransaction getById(int id) {
        return this.PersonalTransaction.getById(id);
    }

    @Override
    @Transactional
    public boolean addOrUpdate(PersonalTransaction personalTransaction) {
        return this.PersonalTransaction.addOrUpdate(personalTransaction);
    }

}
