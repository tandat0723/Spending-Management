/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btl.repository.impl;

import com.btl.pojo.UserRole;
import com.btl.repository.UserRoleRepository;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author phuan
 */
@Repository
@Transactional
public class UserRoleRepositoryImpl implements UserRoleRepository{

    @Autowired
    private LocalSessionFactoryBean factory;
    
    @Override
    public List<UserRole> getUserRole() {
        Session s = factory.getObject().getCurrentSession();
        Query q = s.createQuery("From UserRole");
        return q.getResultList();
    }
    
}
