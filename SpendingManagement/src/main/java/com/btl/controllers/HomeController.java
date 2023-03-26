package com.btl.controllers;

import com.btl.pojo.Category;
import com.btl.pojo.Feature;
import com.btl.service.CategoryService;
import com.btl.service.FeatureService;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    private FeatureService featureService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping(path = {"/", "/features"})
    public String index(Model model, @RequestParam Map<String, String> params) {
        List<Category> cates = this.categoryService.getCategories();
        model.addAttribute("categories", cates);

        List<Feature> features = this.featureService.getFeatures(params);
        model.addAttribute("features", features);

        return "index";
    }
    
    @RequestMapping("/features/{featureId}")
    public String details(Model model, @PathVariable(value= "featureId") int id){
        model.addAttribute("feature", this.featureService.getFeatureById(id));
        
        return "feature-detail";
    }
}
