package com.xmy.portal.controller;

import com.xmy.portal.utils.UploadFile;
import com.xmy.portal.utils.UploadUtil;
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


    @CrossOrigin
    @RequestMapping(value = "/upload")
    @ResponseBody
    public String uploadFj(HttpServletRequest request, HttpSession session) {
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        List<UploadFile> fileList = UploadUtil.getUploadFiles(multiRequest, "F"+"://"+"plantpictureurl/", session);
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

}
