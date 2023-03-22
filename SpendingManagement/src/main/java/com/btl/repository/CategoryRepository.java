/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btl.repository;

import com.btl.pojo.Category;

import java.util.List;

/**
 * @author trant
 */
public interface CategoryRepository {
    List<Category> getCategories();
}
