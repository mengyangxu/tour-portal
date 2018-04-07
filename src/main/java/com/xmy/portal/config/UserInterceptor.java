package com.xmy.portal.config;

import com.xmy.portal.utils.UrlStatic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * file: UserInterceptor.java
 * Created by jiaobuchong on 12/23/15.
 */
public class UserInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(UserInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(null!=request.getSession().getAttribute("user")){
            return true;
        } else {
            String url = request.getRequestURL().toString();
            request.getSession().setAttribute("redirectUrl", url);
            response.sendRedirect(UrlStatic.indexUrl+"user/tologin");
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}