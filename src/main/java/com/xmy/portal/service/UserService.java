package com.xmy.portal.service;

import com.xmy.bean.vo.ArticleInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Description:
 * @Author: xumengyang
 * @Date: Created in 16:02 2018/2/27
 */
@FeignClient(value = "tour-service")
public interface UserService {
    @RequestMapping("/login")
    String login();

    @RequestMapping("/articleInfoList")
    List<ArticleInfo> getArticleInfo();

}
