/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btl.utils;

/**
 * @author trant
 */
public class utils {
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
