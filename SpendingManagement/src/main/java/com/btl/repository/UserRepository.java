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

    User GetById(int id);

//    boolean AddOrUpdate(User user);
    User GetByUserName(String username);

    List<User> GetUsers(String username, int page);

    List<User> GetByEmail(String email);

    List<User> GetByPhone(String phone);
    List<User> getUsers(Map<String, String> params);
    User getUserById(int id);
    boolean addOrUpdateUser(User u);
    boolean deleteUser(int id);
}
