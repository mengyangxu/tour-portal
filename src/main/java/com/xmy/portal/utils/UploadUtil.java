package com.xmy.portal.utils;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Description:
 * @Author: xumengyang
 * @Date: Created in 11:01 2018/3/27
 */
public class UploadUtil {
    public static List<UploadFile> getUploadFiles(MultipartHttpServletRequest multipartHttpServletRequest, String Path, HttpSession session) {
        List<MultipartFile> files = multipartHttpServletRequest
                .getFiles("files");
        List<UploadFile> fileList = new ArrayList<UploadFile>();
        String articlePicString = "";
        for (MultipartFile file : files) {
            // 取得上传文件
            String fileName = file.getOriginalFilename();
            Long fileSize = file.getSize();
            if (null != fileName && !"".equals(fileName)) {
                try {
                    // 创建文件要保存的路径
                    File uploadFile = new File(Path);
                    if (!uploadFile.exists() || null == uploadFile) {
                        uploadFile.mkdirs();
                    }
                    // 获取文件类型
                    String fileType = fileName.substring(
                            fileName.lastIndexOf(".") + 1, fileName.length());
                    String id = UUID.randomUUID().toString();
                    if("".equals(articlePicString)){
                        articlePicString = id;
                    }else{
                        articlePicString += articlePicString + "," + id;
                    }

                    String targetName = id + "." + fileType;
                    // 文件真实存放路径
                    String filePath = uploadFile.getPath() + File.separator
                            + targetName;
                    // 保存文件
                    file.transferTo(new File(filePath));
                    // 初始化上传文件
                    UploadFile up = new UploadFile(id, fileName, fileType,
                            fileSize + "", filePath);
                    fileList.add(up);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        session.setAttribute("articlePicString",articlePicString);
        return fileList;
    }
}
