/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btl.repository.impl;

import com.btl.pojo.User;
import com.btl.repository.UserRepository;
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
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    private final int maxPage = 10;

    private int GetMaxPage() {
        return maxPage;
    }

    @Override
    public User GetById(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        return session.get(User.class, id);
    }

    @Override
    public User GetByUserName(String username) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root root = query.from(User.class);
        query = query.select(root);

        query = query.where(builder.equal(root.get("username").as(String.class), username));
        org.hibernate.query.Query q = session.createQuery(query);
        return (User) q.getSingleResult();
    }

    @Override
    public boolean AddOrUpdate(User user) {
        Session session = this.factory.getObject().getCurrentSession();
        try {
            if (user.getId() != 0)
                session.update(user);
            else
                session.save(user);

            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }

        return false;
    }
}
