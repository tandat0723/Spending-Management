/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btl.repository.impl;

import com.btl.pojo.PersonalTransaction;
import com.btl.pojo.TransactionType;
import com.btl.repository.PersonalTransactionRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
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
    
    private final int maxItemsInPage = 10;

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

    @Override
    public List<PersonalTransaction> getAllPersonalTransaction(Map<String, String> params) {
        Session s = factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<PersonalTransaction> q = b.createQuery(PersonalTransaction.class);
        Root root = q.from(PersonalTransaction.class);
        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();
            String kw = params.get("kw");
            if (kw != null && !kw.isEmpty()) {
                Predicate p = b.like(root.get("name").as(String.class),
                        String.format("%%%s%%", kw));
                predicates.add(p);
            }
            q.where(predicates.toArray(Predicate[]::new));
        }

        q.orderBy(b.desc(root.get("id")));
        Query query = s.createQuery(q);
        List<PersonalTransaction> personal = query.getResultList();

        return personal;
    }

    @Override
    public List<TransactionType> getAllTransactionType(Map<String, String> params) {
        Session s = factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<TransactionType> q = b.createQuery(TransactionType.class);
        Root root = q.from(TransactionType.class);
        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();
            String kw = params.get("kw");
            if (kw != null && !kw.isEmpty()) {
                Predicate p = b.like(root.get("name").as(String.class),
                        String.format("%%%s%%", kw));
                predicates.add(p);
            }
            q.where(predicates.toArray(Predicate[]::new));
        }

        q.orderBy(b.desc(root.get("id")));
        Query query = s.createQuery(q);
        List<TransactionType> transactionTypes = query.getResultList();

        return transactionTypes;
    }

    @Override
<<<<<<< HEAD
    public boolean deleteSpending(int id) {
        PersonalTransaction p = this.getById(id);
        Session s = this.factory.getObject().getCurrentSession();
        try {
            s.delete(p);
            return true;
        } catch (HibernateException ex) {
            return false;
        }
=======
    public int getMaxItemsInPage() {
        return maxItemsInPage;
>>>>>>> 913c752cfa06884f930cf1c0f939867b4c937bcf
    }

}
