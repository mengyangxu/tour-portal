package com.xmy.portal.controller;


import com.xmy.bean.vo.ArticleInfo;
import com.xmy.portal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * Created by 杨波 on 2017/5/28.
 */
@Controller
public class PageController {

    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String index(HttpServletRequest request){
        /*Page<Theme> themeAll = postService.findThemeAll(0);
        List<Theme> themeList = themeAll.getContent();
        int number = themeAll.getNumber();
        int totalPages = themeAll.getTotalPages();
        long all = themeAll.getTotalElements();
        model.addAttribute("all", all);
        model.addAttribute("total", totalPages);
        model.addAttribute("number", number+1);
        model.addAttribute("themeList", themeList);*/
        List<ArticleInfo> list = userService.getArticleInfo();
        request.setAttribute("list", list);
        return "index";
    }

    @RequestMapping("/my")
    public String my(){
        return "my";
    }

    @RequestMapping("/tologin")
    public String login(){
        return "login";
    }

    @RequestMapping("/toregister")
    public String regist(){
        return "register";
    }

    @RequestMapping("/toforgot")
    public String forgot(){
        return "forgot";
    }

    @RequestMapping("toup")
    public String upload(){
        return "fileup";
    }
    /*
    @RequestMapping("/logined")
    public String logined(User user, HttpSession session, Model model){
    	System.out.println("用户名："+user.getUsername());
        User user1 = postService.findUser(user);
        if (user1 != null){
            session.setAttribute("sessionUser", user1);
            Page<Theme> themeAll = postService.findThemeAll(0);
            List<Theme> themeList = themeAll.getContent();
            int number = themeAll.getNumber();
            int totalPages = themeAll.getTotalPages();
            long all = themeAll.getTotalElements();
            model.addAttribute("all", all);
            model.addAttribute("total", totalPages);
            model.addAttribute("number", number+1);
            model.addAttribute("themeList", themeList);
            return "theme";
        }else {
            return "error";
        }

    }*/

    /*@RequestMapping("/logout")
    public String logout(HttpSession session, Model model){
        session.removeAttribute("sessionUser");
        Page<Theme> themeAll = postService.findThemeAll(0);
        List<Theme> themeList = themeAll.getContent();
        int number = themeAll.getNumber();
        int totalPages = themeAll.getTotalPages();
        long all = themeAll.getTotalElements();
        model.addAttribute("all", all);
        model.addAttribute("total", totalPages);
        model.addAttribute("number", number+1);
        model.addAttribute("themeList", themeList);
        return "theme";
    }*/

}
