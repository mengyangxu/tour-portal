package com.xmy.portal.controller;


import com.xmy.bean.bean.User;
import com.xmy.bean.common.Page;
import com.xmy.bean.vo.ArticleInfo;
import com.xmy.portal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;



@Controller
public class PageController {

    @Autowired
    private UserService userService;

    final int pageSize = 3;

    @RequestMapping("/index")
    public String index(HttpServletRequest request){
        Page page = null;
        int currentPage = 1;
        if(null==request.getAttribute("currentPage")){
            currentPage = 1;
        } else {
            String currentPageStr = request.getAttribute("currentPage").toString();
            currentPage = Integer.valueOf(currentPageStr);
        }
        page = new Page(pageSize,0,currentPage);
        List<ArticleInfo> list = userService.getArticleInfo(page);
        int totalResult = userService.getArticleNum();
        page = new Page(pageSize,totalResult,currentPage);
        request.setAttribute("list", list);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPage",page.getTotalPage());
        return "index";
    }

    @RequestMapping("/my")
    public String my(HttpSession session, HttpServletRequest request){
        User user = (User)session.getAttribute("user");
        List<ArticleInfo> list = userService.getArticleInfoById(user.getId());
        User uu = userService.getById(user.getId());
        request.setAttribute("list",list);
        request.setAttribute("user",uu);
        return "my";
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
