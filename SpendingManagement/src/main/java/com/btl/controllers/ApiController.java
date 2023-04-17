/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btl.controllers;

import com.btl.pojo.User;
import com.btl.service.UserService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author trant
 */
@RestController
public class ApiController {

    @Autowired
    private BCryptPasswordEncoder bcryptPasswordEncoder;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/api/check-password", method = RequestMethod.POST, produces = {
        MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity<Map<String, String>> checkPassword(@RequestBody(required = false) Map<String, String> params) {
        HashMap<String, String> map = new HashMap<>();
        boolean check;

        User user = userService.getUserById(Integer.parseInt(params.getOrDefault("id", "0")));
        String rawPassword = params.get("rawPassword");
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        check = bcryptPasswordEncoder.matches(rawPassword, user.getPassword());
        map.put("status", Boolean.toString(check));

        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    @RequestMapping(value = "/api/change-password", method = RequestMethod.POST, produces = {
        MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity<Map<String, String>> changePassword(@RequestBody(required = false) Map<String, String> params) {
        HashMap<String, String> map = new HashMap<>();
        boolean check;
        int id = Integer.parseInt(params.getOrDefault("id", "0"));
        String rawPassword = params.get("newPassword");
        
        try {
            check = userService.changePassword(id, rawPassword);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        map.put("status", Boolean.toString(check));
        
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
