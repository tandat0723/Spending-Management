/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btl.formatter;

import com.btl.pojo.UserRole;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author phuan
 */
public class UserRoleFormatter implements Formatter<UserRole>{

    @Override
    public String print(UserRole userRole, Locale locale) {
        return String.valueOf(userRole.getId());
    }

    @Override
    public UserRole parse(String id, Locale locale) throws ParseException {
        UserRole c = new UserRole();
        c.setId(Integer.parseInt(id));
        
        return c;
    }
    
}
