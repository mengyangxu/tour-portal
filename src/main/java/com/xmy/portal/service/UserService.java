package com.xmy.portal.service;

import com.xmy.bean.bean.User;
import com.xmy.bean.vo.ArticleInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping("/articleInfoList")
    List<ArticleInfo> getArticleInfo();

}
