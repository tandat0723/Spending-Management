/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btl.repository.impl;

import com.btl.pojo.PersonalTransaction;
import com.btl.repository.PersonalTransactionRepository;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author trant
 */
@Repository
@Transactional
public class PersonalTransactionRepositoryImpl implements PersonalTransactionRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public PersonalTransaction getById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(PersonalTransaction.class, id);
    }

    @Override
    public boolean addOrUpdate(PersonalTransaction personalTransaction) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            if (personalTransaction.getId() > 0) {
                s.update(personalTransaction);
            } else {
                s.save(personalTransaction);
            }
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public PersonalTransaction getByUserId(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<PersonalTransaction> query = builder.createQuery(PersonalTransaction.class);
        Root root = query.from(PersonalTransaction.class);
        query = query.select(root);
        
        query = query.where(builder.equal(root.join("user").get("id").as(Integer.class), id));
        org.hibernate.query.Query q = session.createQuery(query);
        
        return (PersonalTransaction) q.getSingleResult();
    }

}
