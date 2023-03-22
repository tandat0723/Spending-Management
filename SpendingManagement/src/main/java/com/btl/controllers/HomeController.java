package com.btl.controllers;

import com.btl.pojo.Category;
import com.btl.pojo.Subcategory;
import com.btl.service.CategoryService;

import java.util.ArrayList;
import java.util.List;

import com.btl.service.SubcategoryService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    private SubcategoryService subcategoryService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping(path = "/")
    public String index(Model model, @RequestParam Map<String, String> params) {
        List<Category> cates = this.categoryService.getCategories();
        model.addAttribute("categories", cates);

        List<Subcategory> sub = this.subcategoryService.getSubcategories(params);
        model.addAttribute("subcategories", sub);

        return "index";
    }
}
