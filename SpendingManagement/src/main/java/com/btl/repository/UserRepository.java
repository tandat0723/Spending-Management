/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btl.repository;

import com.btl.pojo.User;


/**
 * @author trant
 */
public interface UserRepository {
    User GetById(int id);

    boolean AddOrUpdate(User user);

    User GetByUserName(String username);
}
