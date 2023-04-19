/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btl.formatter;

import com.btl.pojo.Status;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author phuan
 */
public class StatusFormatter implements Formatter<Status>{

    @Override
    public String print(Status status, Locale locale) {
        return String.valueOf(status.getId());
    }

    @Override
    public Status parse(String id, Locale locale) throws ParseException {
        Status c = new Status();
        c.setId(Integer.parseInt(id));
        
        return c;
    }
    
}
