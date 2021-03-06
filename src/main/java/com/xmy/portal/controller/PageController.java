package com.xmy.portal.controller;


import com.xmy.bean.bean.Article;
import com.xmy.bean.bean.Plate;
import com.xmy.bean.bean.User;
import com.xmy.bean.common.Page;
import com.xmy.bean.vo.ArticleInfo;
import com.xmy.portal.service.ChatService;
import com.xmy.portal.service.UserService;
import com.xmy.portal.utils.UrlStatic;
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
    @Autowired
    private ChatService chatService;

    final int pageSize = 3;

    @RequestMapping("/index")
    public String index(HttpServletRequest request){
        Page page = null;
        /*int currentPage = 1;
        if(null==request.getParameter("currentPage")){
            currentPage = 1;
        } else {
            String currentPageStr = request.getParameter("currentPage").toString();
            currentPage = Integer.valueOf(currentPageStr);
        }
        //校验登陆
        if(currentPage>1&&null==request.getSession().getAttribute("user")){
            return "redirect:"+ UrlStatic.indexUrl+"user/tologin";
        }*/

        //page = new Page(pageSize,0,currentPage);
        //List<ArticleInfo> list = userService.getArticleInfo(page);
        //list = this.check(list);
        List<User> adverts = userService.getAdverts();
        List<Plate> plates = userService.getPlates();
        //int totalResult = userService.getArticleNum();
        //page = new Page(pageSize,totalResult,currentPage);
        //request.setAttribute("list", list);
        request.setAttribute("adverts",adverts);
        request.setAttribute("plates",plates);
        //request.setAttribute("currentPage", currentPage);
        //request.setAttribute("totalPage",page.getTotalPage());
        request.getSession().setAttribute("serviceUrl",UrlStatic.serviceUrl);

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

    @RequestMapping("/other")
    public String other(HttpSession session, HttpServletRequest request){
        String id = request.getParameter("userId").toString();
        int userId = Integer.valueOf(id);

        //判断是不是自己
        User user = (User)session.getAttribute("user");
        if(user.getId()==userId){
            return "redirect:/my";
        }
        List<ArticleInfo> list = userService.getArticleInfoById(userId);
        User uu = userService.getById(userId);
        request.setAttribute("list",list);
        request.setAttribute("user",uu);
        return "other";
    }




    @RequestMapping("/toforgot")
    public String forgot(){
        return "forgot";
    }

    @RequestMapping("toup")
    public String upload(){
        return "fileup";
    }


    List<ArticleInfo> check(List<ArticleInfo> list){
        for(ArticleInfo info: list){
            if(null!=info.getPics()&&!"".equals(info.getPics())) {
                String pics = info.getPics();
                String[] pic = pics.split(",");
                info.setPics(pic[0]);
            }
        }
        return list;
    }

}
