package com.xmy.portal.utils;

import org.apache.commons.io.FileUtils;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by xmy on 2018/4/5.
 */
public class JerseyUpload {

    public static void upload(File file, String picurl) throws IOException{
        //实例化一个Jersey
        Client client = new Client();

        //设置请求路径
        WebResource resource = client.resource(picurl);
        //读取图片到内存,将其变成二进制数组
        byte[] readFileToByteArray = FileUtils.readFileToByteArray(file);
        //发送post get put
        resource.put(String.class, readFileToByteArray);
        System.out.println("成功发送");
    }
}
