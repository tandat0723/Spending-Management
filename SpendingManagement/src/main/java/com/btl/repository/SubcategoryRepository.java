/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btl.repository;

import com.btl.pojo.Subcategory;

import java.util.List;
import java.util.Map;

/**
 *
 * @author trant
 */
public interface SubcategoryRepository {
    List<Subcategory> getSubcategories(Map<String, String> params);
}
