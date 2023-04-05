/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btl.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author trant
 */
public class utils {
    public static Date stringToDate(String strDate, String pattern) throws ParseException {
        return new SimpleDateFormat(pattern).parse(strDate);
    }
    
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    
    public static boolean isValidEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public static boolean isValidMobile(String phoneStr) {
        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher = pattern.matcher(phoneStr);
        return matcher.matches();
    }
    
    public static String capitalizeWord(String str) {
        String word[] = str.split("");
        String capitalizeWord = "";
        for (String w : word) {
            String first = w.substring(0, 1);
            String after = w.substring(1);
            capitalizeWord += first.toUpperCase() + after + " ";
        }

        return capitalizeWord.trim();
    }

    public static final String stringNormalization(String str) {
        String result = str;
        result = result.trim();
        result = result.replaceAll("\\s+", "");
        result = capitalizeWord(result);

        return result;
    }
}
