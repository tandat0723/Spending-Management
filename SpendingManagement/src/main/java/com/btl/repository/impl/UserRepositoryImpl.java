/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btl.repository.impl;

import com.btl.pojo.Status;
import com.btl.pojo.User;
import com.btl.repository.UserRepository;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private final int maxItemsInPage = 10;

    @Override
    public int getMaxItemsInPage() {
        return maxItemsInPage;
    }

    @Override
    public User getUserById(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        return session.get(User.class, id);
    }

    @Override
    public User getByUserName(String username) {
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
    public List<User> getByEmail(String email) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root root = query.from(User.class);
        query = query.select(root);

        if (!email.isEmpty()) {
            Predicate p = builder.equal(root.get("email").as(String.class), email.trim());
            query = query.where(p);
        }
        query = query.orderBy(builder.desc(root.get("id")));

        Query q = session.createQuery(query);

        return q.getResultList();
    }

    @Override
    public List<User> getByPhone(String phone) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root root = query.from(User.class);
        query = query.select(root);

        if (!phone.isEmpty()) {
            Predicate p = builder.equal(root.get("phone").as(String.class), phone.trim());
            query = query.where(p);
        }

        query = query.orderBy(builder.desc(root.get("id")));

        Query q = session.createQuery(query);

        return q.getResultList();
    }

    @Override
    public boolean addOrUpdateUser(User u) {

        Session s = this.factory.getObject().getCurrentSession();
        try {
            if (u.getId() > 0) {
                s.update(u);
            } else {
                s.save(u);
            }

            return true;
        } catch (HibernateException ex) {
            return false;
        }
    }

    @Override
    public boolean deleteUser(int id) {

        User u = this.getUserById(id);
        Session s = this.factory.getObject().getCurrentSession();
        try {
            u.setActive(new Status(2));
            s.update(u);
            return true;
        } catch (HibernateException ex) {
            return false;
        }
    }

    @Override
    public List<User> getUsers(String username, int page) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root root = query.from(User.class);
        query = query.select(root);

        if (username != null && !username.isEmpty()) {
            Predicate p = builder.equal(root.get("username").as(String.class), username.trim());
            query = query.where(p);
        }
        query = query.orderBy(builder.desc(root.get("id")));
        Query q = session.createQuery(query);

        if (page != 0) {
            int max = maxItemsInPage;
            q.setMaxResults(max);
            q.setFirstResult((page - 1) * max);
        }

        return q.getResultList();
    }

    public List<User> getByRole(String role, int page, int active) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root root = query.from(User.class);
        query = query.select(root);

        Predicate p1 = builder.equal(root.get("userRole").as(String.class), role.trim());
        Predicate p2 = builder.equal(root.get("userRole").as(String.class), role.trim());

        query = query.where(p1, p2);
        query = query.orderBy(builder.desc(root.get("id")));
        Query q = session.createQuery(query);
        if (page != 0) {
            int max = maxItemsInPage;
            q.setMaxResults(max);
            q.setFirstResult((page - 1) * max);
        }

        return q.getResultList();
    }

    @Override
    public List<User> getUsersMultiCondition(Map<String, String> params, int page) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> q = builder.createQuery(User.class);
        Root root = q.from(User.class);
        q.select(root);
        q = q.orderBy(builder.desc(root.get("id")));

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();
            if (params.containsKey("firstname")) {
                Predicate p1 = builder.like(root.get("firstName").as(String.class),
                        String.format("%%%s%%", params.get("firstname")));
                predicates.add(p1);
            }
            if (params.containsKey("lastname")) {
                Predicate p2 = builder.like(root.get("lastName").as(String.class),
                        String.format("%%%s%%", params.get("lastname")));
                predicates.add(p2);
            }
            if (params.containsKey("userRole")) {
                Predicate p3 = builder.equal(root.get("userRole").as(String.class), params.get("userRole"));
                predicates.add(p3);
            }
            if (params.containsKey("active")) {
                int activeStt = Integer.parseInt(params.get("active"));
                Predicate p4 = builder.equal(root.get("active").as(Integer.class), activeStt);
                predicates.add(p4);
            }
            if (params.containsKey("username")) {
                Predicate p5 = builder.like(root.get("username").as(String.class),
                        String.format("%%%s%%", params.get("username")));
                predicates.add(p5);
            }
            if (params.containsKey("phone")) {
                Predicate p6 = builder.like(root.get("phone").as(String.class),
                        String.format("%%%s%%", params.get("phone")));
                predicates.add(p6);
            }
            if (params.containsKey("email")) {
                Predicate p7 = builder.like(root.get("email").as(String.class),
                        String.format("%%%s%%", params.get("email")));
                predicates.add(p7);
            }
            if (params.containsKey("id")) {
                int id = Integer.parseInt(params.get("id"));
                Predicate p8 = builder.equal(root.get("id").as(Integer.class), id);
                predicates.add(p8);
            }

            q = q.where(predicates.toArray(new Predicate[]{}));
        }
        Query query = session.createQuery(q);

        if (page != 0) {
            int max = maxItemsInPage;
            query.setMaxResults(max);
            query.setFirstResult((page - 1) * max);
        }

        return query.getResultList();
    }

    @Override
    public boolean changePassword(int id, String rawPassword) {
        User user = getUserById(id);
        if (user == null) {
            return false;
        } else {
            String password = bCryptPasswordEncoder.encode(rawPassword);
            user.setPassword(password);
            addOrUpdateUser(user);
            return true;
        }
    }

    @Override
    public long count() {
        Session session = this.factory.getObject().getCurrentSession();
        Query q = session.createQuery("Select Count(*) From User");

        return Long.parseLong(q.getSingleResult().toString());
    }

    @Override
    public List<User> getAllUsers(Map<String, String> params) {
        Session s = factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<User> q = b.createQuery(User.class);
        Root root = q.from(User.class);
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
        List<User> users = query.getResultList();

        return users;
    }

}
