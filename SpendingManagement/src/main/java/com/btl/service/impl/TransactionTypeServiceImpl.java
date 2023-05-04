/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btl.service.impl;

import com.btl.pojo.TransactionType;
import com.btl.repository.TransactionTypeRepository;
import com.btl.service.TransactionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Service;

/**
 *
 * @author trant
 */
@Service
public class TransactionTypeServiceImpl implements TransactionTypeService{
    @Autowired
    private TransactionTypeRepository transactionTypeRepository;
    
    @Override
    public TransactionType getById(int id) {
        return this.transactionTypeRepository.getById(id);
    }
    
}
