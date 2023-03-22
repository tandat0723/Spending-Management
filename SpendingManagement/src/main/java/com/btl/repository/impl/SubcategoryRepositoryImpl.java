/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btl.repository.impl;

import com.btl.pojo.Subcategory;
import com.btl.repository.SubcategoryRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author trant
 */
@Repository
@Transactional
public class SubcategoryRepositoryImpl implements SubcategoryRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Subcategory> getSubcategories(Map<String, String> params) {
        Session s = factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Subcategory> q = b.createQuery(Subcategory.class);
        Root root = q.from(Subcategory.class);
        q.select(root);

        List<Predicate> predicates = new ArrayList<>();
        String kw = params.get("kw");
        if (kw != null && !kw.isEmpty()) {
            Predicate p = b.like(root.get("name").as(String.class),
                    String.format("%%%s%%", kw));
            predicates.add(p);
        }

        String cateId = params.get("categoryId");
        if (cateId != null) {
            Predicate p = b.lessThanOrEqualTo(root.get("categoryId"),
                    Integer.parseInt(cateId));
            predicates.add(p);
        }

        q.where(predicates.toArray(Predicate[]::new));
        q.orderBy(b.desc(root.get("id")));
        Query query = s.createQuery(q);
        List<Subcategory> subcategories = query.getResultList();

        return subcategories;
    }

    @Override
    public Subcategory getSubcategoryById(int id) {
        Session s = this.factory.getObject().getCurrentSession();

        return s.get(Subcategory.class, id);
    }
}
