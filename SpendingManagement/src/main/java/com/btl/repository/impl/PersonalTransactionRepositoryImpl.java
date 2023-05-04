/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btl.repository.impl;

import com.btl.pojo.PersonalTransaction;
import com.btl.pojo.TransactionType;
import com.btl.repository.PersonalTransactionRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    private PersonalTransactionRepository personalTransactionRepository;
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
            q.where(predicates.toArray(new Predicate[]{}));
        }

        q.orderBy(b.desc(root.get("id")));
        Query query = s.createQuery(q);
        List<TransactionType> transactionTypes = query.getResultList();

        return transactionTypes;
    }

    @Override
    public boolean deleteSpending(int id) {
        PersonalTransaction p = this.getById(id);
        Session s = this.factory.getObject().getCurrentSession();
        try {
            s.delete(p);
            return true;
        } catch (HibernateException ex) {
            return false;
        }
    }
    @Override
    public int getMaxItemsInPage() {
        return maxItemsInPage;
    }

    @Override
    public List<PersonalTransaction> getPersonalTransaction(Map<String, String> params, int page, int maxItems) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<PersonalTransaction> query = builder.createQuery(PersonalTransaction.class);
        Root root = query.from(PersonalTransaction.class);
        query.select(root);
        query = query.orderBy(builder.desc(root.get("date")));

        if (params != null) {
            List<Predicate> p = new ArrayList<>();
            if (params.containsKey("name")) {
                Predicate p1 = builder.like(root.get("name").as(String.class),
                        String.format("%%%s%%", params.get("name")));
                p.add(p1);
            }

            if (params.containsKey("description")) {
                Predicate p2 = builder.like(root.get("description").as(String.class),
                        String.format("%%%s%%", params.get("description")));
                p.add(p2);
            }

            if (params.containsKey("date")) {
                try {
                    Date date = new SimpleDateFormat("dd/MM/yyyy").parse(params.get("date"));
                    Predicate p3 = builder.greaterThan(root.get("date").as(Date.class), date);
                    p.add(p3);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            if (params.containsKey("transactionTypeId")) {
                Predicate p4 = builder.equal(root.join("transactionType").get("id").as(String.class),
                        params.get("transactionTypeId"));
                p.add(p4);
            }

            if (params.containsKey("addPersonalTransactionUserId")) {
                Predicate p5 = builder.equal(root.join("userId").get("id").as(String.class),
                        params.get("addPersonalTransactionUserId"));
                p.add(p5);
            }

            if (params.containsKey("purpose")) {
                Predicate p6 = builder.equal(root.get("purpose").as(String.class),
                        String.format("%%%s%%", params.get("purpose")));
                p.add(p6);
            }

            if (params.containsKey("price")) {
                Predicate p7 = builder.equal(root.get("price").as(String.class),
                        String.format("%%%s%%", params.get("price")));
                p.add(p7);
            }

            query = query.where(p.toArray(new Predicate[]{}));

            if (params.containsKey("sort")) {
                if (params.get("sort").equals("asc")) {
                    query = query.orderBy(builder.asc(root.get("date")));
                } else {
                    query = query.orderBy(builder.desc(root.get("date")));
                }
            }
        }

        Query q = session.createQuery(query);
        if (page != 0) {
            int test;
            if (maxItems == 0) {
                test = (page - 1) * maxItemsInPage;
                q.setFirstResult(test);
                q.setMaxResults(maxItemsInPage);
            } else {
                test = (page - 1) * maxItems;
                q.setFirstResult(test);
                q.setMaxResults(maxItems);
            }
        }
        return q.getResultList();
    }

}
