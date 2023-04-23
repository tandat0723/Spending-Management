package com.btl.service;

import com.btl.pojo.User;
import java.util.List;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User getUserById(int id);

    boolean addOrUpdateUser(User user);

    User getByUsername(String username);

    List<User> getByEmail(String email);

    List<User> getByPhone(String phone);

    List<User> getUsers(String username, int page);
    
    List<User> getAllUsers(Map<String, String> params);

    boolean addOrUpdateNoPassword(User user);
    
    boolean changePassword(int id, String rawPassword);

    boolean deleteUser(int id);

    int getMaxItemsInPage();
}
