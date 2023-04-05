package com.btl.service.impl;

import com.btl.pojo.User;
import com.btl.repository.UserRepository;
import com.btl.service.UserService;
import com.btl.utils.utils;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
//    @Autowired
//    private Cloudinary cloudinary;
//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User GetById(int id) {
        return this.userRepository.GetById(id);
    }

//    @Override
//    public boolean AddOrUpdate(User user) {
//        String pass = user.getPassword().trim();
//        user.setPassword(this.passwordEncoder.encode(pass));
//
//        if (user.getLastName() != null) {
//            String lastName = user.getLastName();
//            user.setLastName(utils.stringNormalization(lastName));
//        }
//
//        String avatar = user.getAvatar();
//        if (!user.getFile().isEmpty()) {
//            Map result = null;
//            try {
//                result = this.cloudinary.uploader().upload(user.getFile().getBytes(),
//                        ObjectUtils.asMap("resource_type", "auto"));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            if (result != null) {
//                user.setAvatar((String) result.get("secure_url"));
//            } else {
//                user.setAvatar(avatar);
//            }
//        }
//
//        if (user.getId() == 0) {
//            user.setJoinedDate(new Date());
//        }
//        return this.userRepository.AddOrUpdate(user);
//    }
    @Override
    public User getByUsername(String username) {
        return this.userRepository.GetByUserName(username);
    }

    @Override
    public List<User> GetUsers(String username, int page) {
        return this.userRepository.GetUsers(username, page);
    }

    @Override
    public List<User> GetByEmail(String email) {
        return this.userRepository.GetByEmail(email);
    }
    @Override
    public List<User> GetByPhone(String phone) {
        return this.userRepository.GetByPhone(phone);
    }
}
