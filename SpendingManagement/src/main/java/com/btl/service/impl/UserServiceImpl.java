package com.btl.service.impl;

import com.btl.pojo.User;
import com.btl.repository.UserRepository;
import com.btl.service.UserService;
import com.btl.utils.utils;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User getById(int id) {
        return this.userRepository.getById(id);
    }
    

    @Override
    public boolean addOrUpdate(User user) {
        String pass = user.getPassword().trim();
        user.setPassword(this.bCryptPasswordEncoder.encode(pass));

        if (user.getLastName() != null) {
            String lastName = user.getLastName();
            user.setLastName(utils.stringNormalization(lastName));
        }

        String avatar = user.getAvatar();
        if (!user.getFile().isEmpty()) {
            Map result = null;
            try {
                result = this.cloudinary.uploader().upload(user.getFile().getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (result != null) {
                user.setAvatar((String) result.get("secure_url"));
            } else {
                user.setAvatar(avatar);
            }
        }

        if (user.getId() == 0) {
            user.setJoinedDate(new Date());
        }
        return this.userRepository.addOrUpdate(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User getByUsername(String username) {
        return this.userRepository.getByUserName(username);
    }

    @Override
    public List<User> getUsers(String username, int page) {
        return this.userRepository.getUsers(username, page);
    }

    @Override
    public List<User> getByEmail(String email) {
        return this.userRepository.getByEmail(email);
    }

    @Override
    public List<User> getByPhone(String phone) {
        return this.userRepository.getByPhone(phone);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> users = this.getUsers(username, 0);
        if (users.isEmpty()) {
            throw new UsernameNotFoundException("Người dùng Không tồn tại!");
        }
        User user = users.get(0);
        Set<GrantedAuthority> auth = new HashSet<>();
        auth.add(new SimpleGrantedAuthority(user.getUserRole()));

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), auth);
    }

    @Override
    public boolean addOrUpdateNoPassword(User user) {
        if (user.getLastName() != null) {
            String lastname = user.getLastName();
            user.setLastName(utils.stringNormalization(lastname));
        }

        String avatar = user.getAvatar();
        if (!user.getFile().isEmpty()) {
            Map r = null;
            try {
                r = this.cloudinary.uploader().upload(user.getFile().getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            if (r != null) {
                user.setAvatar((String) r.get("secure_url"));
            } else {
                user.setAvatar(avatar);
            }
        }

        if (user.getId() == 0) {
            user.setJoinedDate(new Date());
        }
        return this.userRepository.addOrUpdate(user);
    }

    @Override
    public int getMaxItemsInPage() {
        return this.userRepository.getMaxItemsInPage();
    }


}
