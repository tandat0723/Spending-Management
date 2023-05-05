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
    
    List<PersonalTransaction> getAllPersonalTransaction(Map<String, String> params);
    
    List<TransactionType> getAllTransactionType(Map<String, String> params);
    
    boolean deleteSpending(int id);
    
    List<PersonalTransaction> getPersonalTransaction(Map<String, String> params, int page, int maxItems);
    
    int getMaxItemsInPage();
}
