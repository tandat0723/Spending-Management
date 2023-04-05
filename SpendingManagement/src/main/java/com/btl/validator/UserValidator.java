/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btl.validator;

import com.btl.pojo.User;
import com.btl.service.UserService;
import com.btl.utils.utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author trant
 */
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        int usernameMinLength = 6;
        int usernameMaxLength = 20;
        int passwordMinLength = 6;

        if (userService.GetUsers(user.getUsername(), 0).size() > 0) {
            errors.rejectValue("username", "", "Tên đăng nhập đã tồn tại");
        }
        if (user.getUsername().isEmpty()) {
            errors.rejectValue("username", "", "Tên đăng nhập không được bỏ trống");
        }
        if (user.getUsername().contains(" ")) {
            errors.rejectValue("username", "", "Tên đăng nhập không có khoảng trắng");
        }
        if (user.getUsername().length() < usernameMinLength) {
            errors.rejectValue("username", "", "Tên đăng nhập không ít hơn " + usernameMinLength + " ký tự");
        }
        if (user.getUsername().length() > usernameMaxLength) {
            errors.rejectValue("username", "", "Tên đăng nhập không quá " + usernameMaxLength + " ký tự");
        }
        if (user.getPassword().isEmpty()) {
            errors.rejectValue("password", "", "Mật khẩu không được bỏ trống");
        }
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            errors.rejectValue("password", "", "Mật khẩu không trùng khớp");
        }
        if (user.getPassword().contains(" ")) {
            errors.rejectValue("password", "", "Mật khẩu không chứa khoảng trắng");
        }
        if (user.getPassword().length() < passwordMinLength) {
            errors.rejectValue("password", "", "Mật khẩu cần có tối thiểu " + passwordMinLength + " ký tự");
        }
        if (userService.GetByEmail(user.getEmail()).size() > 0) {
            errors.rejectValue("email", "", "Email đã tồn tại");
        }
        if (!utils.isValidEmail(user.getEmail())) {
            errors.rejectValue("email", "", "Email chưa đúng định dạng");
        }
        if (userService.GetByPhone(user.getPhone()).size() > 0) {
            errors.rejectValue("phone", "", "Số điện thoại đã tồn tại");
        }
        if (!utils.isValidMobile(user.getPhone())) {
            errors.rejectValue("phone", "", "Số điện thoại không hợp lệ");
        }
    }

}
