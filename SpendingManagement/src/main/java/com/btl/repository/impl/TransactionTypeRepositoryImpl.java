/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btl.repository.impl;

import com.btl.pojo.TransactionType;
import com.btl.repository.TransactionTypeRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author trant
 */
@Repository
@Transactional
public class TransactionTypeRepositoryImpl implements TransactionTypeRepository{
    @Autowired
    private LocalSessionFactoryBean factory;
    
    @Override
    public TransactionType getById(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        return session.get(TransactionType.class, id);
    }
    
}
