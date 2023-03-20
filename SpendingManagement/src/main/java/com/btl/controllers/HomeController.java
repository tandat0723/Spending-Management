package com.btl.controllers;

import com.btl.pojo.Category;
import com.btl.pojo.Subcategory;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String index(Model model, 
            @RequestParam(value = "kw", defaultValue = "") String kw) {
        List<Category> cates = new ArrayList<>();
        cates.add(new Category(1, "Quan ly thu chi"));
        cates.add(new Category(2, "Quan ly nhóm"));
        cates.add(new Category(3, "Quan ly lịch rãnh"));
        
        model.addAttribute("categories", cates);
        
        List<Subcategory> sub = new ArrayList<>();
        sub.add(new Subcategory(1, "Thêm khoản thu chi", "https://nukeviet.vn/uploads/store/images/2018_08/money-bag.png"));
        sub.add(new Subcategory(2, "Xóa khoản thu chi","https://nukeviet.vn/uploads/store/images/2018_08/money-bag.png"));
        sub.add(new Subcategory(3, "Sửa khoản thu chi","https://nukeviet.vn/uploads/store/images/2018_08/money-bag.png"));
        sub.add(new Subcategory(4, "Tạo nhóm", "https://nukeviet.vn/uploads/store/images/2018_08/money-bag.png"));
        
        if(kw != null && !kw.isEmpty())
            sub = sub.stream().filter(s->s.getName().contains(kw)).collect(Collectors.toList());
        
        model.addAttribute("subcategories", sub);
        
        
        return "index";
    }
}
