package com.xmy.portal.controller;

import com.netflix.ribbon.proxy.annotation.Http;
import com.xmy.bean.bean.Article;
import com.xmy.bean.bean.User;
import com.xmy.portal.service.UserService;
import com.xmy.portal.utils.JsonResponse;
import com.xmy.portal.utils.UploadFile;
import com.xmy.portal.utils.UploadUtil;
import feign.Headers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Description:
 * @Author: xumengyang
 * @Date: Created in 10:24 2018/3/27
 */
@Controller
public class ArticleController {

    @Autowired
    private UserService userService;

    @CrossOrigin
    @RequestMapping(value = "/upload")
    @ResponseBody
    public String uploadFj(HttpServletRequest request, HttpSession session) {
        session.removeAttribute("articlePicString");

        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        //List<UploadFile> fileList = UploadUtil.getUploadFiles(multiRequest, "D"+"://"+"plantpictureurl/", session);
        //List<UploadFile> fileList = UploadUtil.getUploadFiles(multiRequest, "http://localhost:8080/img/", session);
        List<UploadFile> fileList = UploadUtil.getUploadFiles(multiRequest, "D:\\Program Files\\apache-tomcat-7.0.82\\webapps\\ROOT\\img/", session);
        //List<UploadFile> fileList = UploadUtil.getUploadFiles(multiRequest, "C:\\Users\\Administrator\\Downloads\\tour-portal\\src\\main\\resources\\static\\img\\upload/", session);
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            for (UploadFile file : fileList) {
                System.out.println("上传文件有：" + file);
            }

            map.put("message", "success");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("meaasge", "failed");
        }
        return "true";
    }

    //@Headers({"Accept: application/json"})
    //@Headers({"dataType: json"})
    @RequestMapping("/addArticle")
    @ResponseBody
    public JsonResponse addArticle(@RequestParam String title, @RequestParam String content, HttpSession session){
        User user = (User)session.getAttribute("user");
        Article article = new Article();
        if(null==user){
            return new JsonResponse(new Exception());
        }
        article.setUserId(user.getId());
        if(null!=session.getAttribute("articlePicString")) {
            article.setPics(session.getAttribute("articlePicString").toString());
        }
        article.setTitle(title);
        article.setContent(content);
        userService.addArticle(article);
        return new JsonResponse("");
    }

}
