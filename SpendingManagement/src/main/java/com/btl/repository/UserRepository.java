/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btl.repository;

import com.btl.pojo.User;

import java.util.List;
import java.util.Map;

/**
 * @author trant
 */
public interface UserRepository {

    User getUserById(int id);

    boolean addOrUpdateUser(User user);

    User getByUserName(String username);

    List<User> getUsersMultiCondition(Map<String, String> params, int page);

    boolean changePassword(int id, String rawPassword);

    List<User> getUsers(String username, int page);
    
    List<User> getAllUsers(Map<String, String> params);

    List<User> getByEmail(String email);

    List<User> getByPhone(String phone);

    List<User> getByRole(String role, int page, int active);

    boolean deleteUser(int id);

    int getMaxItemsInPage();

    long count();
    
}
