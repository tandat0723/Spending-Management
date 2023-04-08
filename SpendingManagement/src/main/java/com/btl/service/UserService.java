package com.btl.service;

import com.btl.pojo.User;
import java.util.List;
import java.util.Map;

public interface UserService {

    User GetById(int id);

//    boolean AddOrUpdate(User user);
    User getByUsername(String username);

    List<User> GetUsers(String username, int page);

    List<User> GetByEmail(String email);

    List<User> GetByPhone(String phone);
    List<User> getUsers(Map<String, String> params);
    User getUserById(int id);
    boolean addOrUpdateUser(User u);
    boolean deleteUser(int id);
}
