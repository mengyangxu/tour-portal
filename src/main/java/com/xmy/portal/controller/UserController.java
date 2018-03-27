package com.xmy.portal.controller;


import com.xmy.bean.bean.User;
import com.xmy.portal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * Created by 杨波 on 2017/5/28.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/login")
    public String login(HttpServletRequest request, HttpSession session){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username+"---"+password);
        String logg = userService.login();
        System.out.println("logg:"+logg);
        User user = new User();
        user.setUsername(username);
        session.setAttribute("user",user);
        return "index";
    }

    @RequestMapping("/register")
    public String register(){
        return "index";
    }


}
