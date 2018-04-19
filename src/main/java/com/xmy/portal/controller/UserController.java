package com.xmy.portal.controller;


import com.xmy.bean.bean.User;
import com.xmy.portal.service.UserService;
import com.xmy.portal.socket.WebSocketTest;
import com.xmy.portal.utils.JsonResponse;
import com.xmy.portal.utils.UrlStatic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Created by 杨波 on 2017/5/28.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/tologin")
    public String login(HttpSession session){
        if(null!=session.getAttribute("user")){
            return "redirect:"+ UrlStatic.indexUrl+"index";
        }
        return "login";
    }

    @RequestMapping("/toregister")
    public String regist(){
        return "register";
    }

    @RequestMapping("/login")
    @ResponseBody
    public JsonResponse login(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = userService.login(username, password);

        if(null!=user){
            session.setAttribute("user",user);

            return  new JsonResponse(UrlStatic.indexUrl+"index");
        }
        return new JsonResponse(new Exception());
    }

    @RequestMapping("/register")
    public String register(){
        return "index";
    }

    @RequestMapping("/newBranch3")
    public String newBranch(){
        return "index";
    }


}
