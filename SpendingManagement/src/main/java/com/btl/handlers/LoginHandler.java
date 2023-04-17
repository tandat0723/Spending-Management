/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btl.handlers;

import com.btl.pojo.User;
import com.btl.service.UserService;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * @author trant
 */
@Component
public class LoginHandler implements AuthenticationSuccessHandler {
    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, 
            HttpServletResponse response, Authentication a) throws IOException, ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = this.userService.getByUsername(authentication.getName());
        request.getSession().setAttribute("currentUser", user);
        System.out.println("LOGIN SUCCESSFUL");
        String redirectStr = request.getContextPath();
        switch (user.getUserRole()) {
            case User.ADMIN:
                redirectStr = "admin";
                break;
            case User.USER:
                redirectStr = "user";
                break;
        }
        response.sendRedirect(redirectStr);
    }
}
