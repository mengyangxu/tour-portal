package com.xmy.portal.service;

import com.xmy.bean.bean.Article;
import com.xmy.bean.bean.User;
import com.xmy.bean.common.Page;
import com.xmy.bean.vo.ArticleInfo;
import com.xmy.portal.utils.JsonResponse;
import feign.Param;
import org.omg.CORBA.Request;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Description:
 * @Author: xumengyang
 * @Date: Created in 16:02 2018/2/27
 */
@FeignClient(value = "tour-service")
@Service
public interface UserService {
    @RequestMapping("/login")
    User login(@RequestParam("username") String username, @RequestParam("password") String password);

    @RequestMapping("/articleInfoPage")
    List<ArticleInfo> getArticleInfo(@RequestBody Page page);

    @RequestMapping(value = "/addArticle" ,method = RequestMethod.POST )
    JsonResponse addArticle(@RequestBody(required = false) Article article);

    @RequestMapping("/getArticleInfosById")
    List<ArticleInfo> getArticleInfoById(@RequestParam("id") Integer id);

    @RequestMapping("/getUserById")
    User getById(@RequestParam("id") Integer id);

    @RequestMapping("/getArticleNum")
    int getArticleNum();

    @RequestMapping("/getAdverts")
    List<User> getAdverts();
}
