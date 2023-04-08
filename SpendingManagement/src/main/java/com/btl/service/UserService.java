package com.btl.service;

import com.btl.pojo.User;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {

    User getById(int id);

    boolean addOrUpdate(User user);

    User getByUsername(String username);

    List<User> getByEmail(String email);

    List<User> getByPhone(String phone);

    boolean addOrUpdateNoPassword(User user);

    List<User> getUsers(Map<String, String> params);
    
    User getUserById(int id);
    
    boolean addOrUpdateUser(User u);
    
    boolean deleteUser(int id);
    
    int getMaxItemsInPage();

}
