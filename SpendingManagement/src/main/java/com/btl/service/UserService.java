package com.btl.service;

import com.btl.pojo.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService{
    User GetById(int id);

    boolean AddOrUpdate(User user);

    User getByUsername(String username);
}
