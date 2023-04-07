/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btl.service;

import com.btl.pojo.PersonalTransaction;

/**
 * @author trant
 */
public interface PersonalTransactionService {

    PersonalTransaction getById(int id);

    boolean addOrUpdate(PersonalTransaction personalTransaction);
    
    PersonalTransaction getByUserId(int id);
}
