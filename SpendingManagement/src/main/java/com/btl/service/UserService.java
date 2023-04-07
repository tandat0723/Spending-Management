package com.btl.service;

import com.btl.pojo.User;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User GetById(int id);

    boolean AddOrUpdate(User user);

    User getByUsername(String username);

    List<User> GetUsers(String username, int page);

    List<User> GetByEmail(String email);

    List<User> GetByPhone(String phone);

    boolean addOrUpdateNoPassword(User user);

    int getMaxItemsInPage();
}
