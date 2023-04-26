/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btl.controllers;

import com.btl.service.UserService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author trant
 */
@Controller
public class PersonalTransactionController {

    @RequestMapping("/user/personal-transaction")
    public String index() {
        return "personal-transaction";
    }

//    @RequestMapping("/user/personal-transaction/management")
//    public String management(Model model, Authentication authentication,
//            @RequestParam(required = false) Map<String, String> params) {
//        //chua check active
//        //
//        //
//        int page = Integer.parseInt(params.getOrDefault("page", "1"));
//        String name = params.getOrDefault("name", null);
//        String type = params.getOrDefault("type", null);
//        String purpose = params.getOrDefault("purpose", null);
//        String description = params.getOrDefault("description", null);
//        String price = params.getOrDefault("price", null);
//
//        Map<String, String> p = new HashMap<>();
//        if (name != null) {
//            p.put("name", name);
//            model.addAttribute("name", name);
//        }
//        if (type != null) {
//            p.put("type", type);
//            model.addAttribute("type", type);
//        }
//        if (purpose != null) {
//            p.put("purpose", purpose);
//            model.addAttribute("purpose", purpose);
//        }
//        if (description != null) {
//            p.put("description", description);
//            model.addAttribute("description", description);
//        }
//        if (price != null) {
//            p.put("price", price);
//            model.addAttribute("price", price);
//        }
//
//        model.addAttribute("errMsg", model.asMap().get("errMsg"));
//        model.addAttribute("sucMsg", model.asMap().get("sucMsg"));
//
//        return "user-management";
//
//    }
}
