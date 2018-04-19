package com.xmy.portal.controller;

import com.netflix.ribbon.proxy.annotation.Http;
import com.xmy.bean.bean.Article;
import com.xmy.bean.bean.User;
import com.xmy.portal.service.UserService;
import com.xmy.portal.utils.*;
import feign.Headers;
import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
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
    public String uploadFj(HttpServletRequest request, HttpSession session) throws Exception{
        session.removeAttribute("articlePicString");

        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        //List<UploadFile> fileList = UploadUtil.getUploadFiles(multiRequest, "D:\\Program Files\\apache-tomcat-7.0.82\\webapps\\ROOT\\img/", session);
        List<MultipartFile> files = multiRequest.getFiles("files");
        StringBuilder pics = new StringBuilder("");
        for(int i=0;i<files.size();i++){
            String picName = UUID.randomUUID().toString()+".jpg";
            String picUrl = UrlStatic.picUrl+"img/article/"+picName;
            File file2 = new File(UrlStatic.pictransit); //临时文件
            FileUtils.copyInputStreamToFile(files.get(i).getInputStream(), file2);
            JerseyUpload.upload(file2,picUrl);
            if(i<files.size()-1) {
                pics.append("/img/article/"+picName + ",");
            } else{
                pics.append("/img/article/"+picName);
            }
        }
        session.setAttribute("articlePicString",pics);
        return "true";
    }

    //@Headers({"Accept: application/json"})
    //@Headers({"dataType: json"})
    @RequestMapping("/addArticle")
    @ResponseBody
    public JsonResponse addArticle(@RequestParam("title") String title, @RequestParam String content, HttpSession session){
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
