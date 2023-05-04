/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btl.service;

import com.btl.pojo.PersonalTransaction;
import com.btl.pojo.TransactionType;
import java.util.List;
import java.util.Map;

/**
 * @author trant
 */
public interface PersonalTransactionService {

    PersonalTransaction getById(int id);

    boolean addOrUpdate(PersonalTransaction personalTransaction);
    
    PersonalTransaction getByUserId(int id);
    
    List<PersonalTransaction> getAllPersonalTransaction(Map<String, String> params);
    
    List<TransactionType> getAllTransactionType(Map<String, String> params);
    
<<<<<<< HEAD
    boolean deleteSpending(int id);
=======
    int getMaxItemsInPage();
>>>>>>> 913c752cfa06884f930cf1c0f939867b4c937bcf
}
