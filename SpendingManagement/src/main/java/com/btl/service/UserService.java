package com.btl.service;

import com.btl.pojo.User;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User getById(int id);

    boolean addOrUpdate(User user);

    User getByUsername(String username);

    List<User> getUsers(String username, int page);

    List<User> getByEmail(String email);

    List<User> getByPhone(String phone);

    boolean addOrUpdateNoPassword(User user);

    int getMaxItemsInPage();
}
