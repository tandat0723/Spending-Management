/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btl.service.impl;

import com.btl.pojo.Subcategory;
import com.btl.repository.SubcategoryRepository;
import com.btl.service.SubcategoryService;
import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author trant
 */
@Service
public class SubcategoryServiceImpl implements SubcategoryService {
    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    @Override
    public List<Subcategory> getSubcategories(Map<String, String> params) {
        return this.subcategoryRepository.getSubcategories(params);
    }

    @Override
    public Subcategory getSubcategoryById(int id) {
        return this.subcategoryRepository.getSubcategoryById(id);
    }
}
