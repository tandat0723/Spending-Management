package com.btl.service.impl;

import com.btl.pojo.User;
import com.btl.repository.UserRepository;
import com.btl.service.UserService;
import com.btl.utils.utils;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    public User getUserById(int id) {
        return this.userRepository.getUserById(id);
    }

    @Override
    public boolean addOrUpdateUser(User user) {
        String pass = user.getPassword().trim();
        user.setPassword(this.bCryptPasswordEncoder.encode(pass));

        if (user.getFullname() != null) {
            String fullname = user.getFullname();
            user.setFullname(utils.stringNormalization(fullname));
        }

        String avatar = user.getAvatar();

        if (!user.getFile().isEmpty()) {
            Map res = null;
            try {
                res = this.cloudinary.uploader().upload(user.getFile().getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                user.setAvatar(res.get("secure_url").toString());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            if (res != null)
                user.setAvatar((String) res.get("secure_url"));
            else
                user.setAvatar(avatar);
        }
        
        if(user.getId() == 0) 
            user.setJoinedDate(new Date());
        return this.userRepository.addOrUpdateUser(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User getByUsername(String username) {
        return this.userRepository.getByUserName(username);
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
    public List<User> getUsers(String username, int page) {
        return this.userRepository.getUsers(username, page);
    }

    @Override
    public boolean deleteUser(int id) {

        return this.userRepository.deleteUser(id);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User users = this.getByUsername(username);
        if (users == null) {
            throw new UsernameNotFoundException("Người dùng Không tồn tại!");
        }

        Set<GrantedAuthority> auth = new HashSet<>();
        auth.add(new SimpleGrantedAuthority(users.getUserRole().getRole()));

        return new org.springframework.security.core.userdetails.User(users.getUsername(),
                users.getPassword(), auth);
    }

    @Override
    public boolean addOrUpdateNoPassword(User user) {
        if (user.getFullname() != null) {
            String fullname = user.getFullname();
            user.setFullname(utils.stringNormalization(fullname));
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
        return this.userRepository.addOrUpdateUser(user);
    }

    @Override
    public int getMaxItemsInPage() {
        return this.userRepository.getMaxItemsInPage();
    }

    @Override
    public boolean changePassword(int id, String rawPassword) {
        return this.userRepository.changePassword(id, rawPassword);
    }

    @Override
    public List<User> getAllUsers(Map<String, String> params) {
        return this.userRepository.getAllUsers(params);
    }
}
