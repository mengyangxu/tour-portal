package com.xmy.portal;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.xmy.portal.activemq.Comsumer;
import com.xmy.portal.activemq.Producter;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.commons.util.IdUtils;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class TourPortalApplicationTests {

	@Test
	public void contextLoads() throws IOException{
		//实例化一个Jersey
		Client client = new Client();
		//保存图片服务器的请求路径
		String picName = UUID.randomUUID().toString()+".jpg";
		String url = "http://localhost:8080/img/"+picName;
		//设置请求路径
		WebResource resource = client.resource(url);
		String path = "G:\\img/5.jpg";
		//读取图片到内存,将其变成二进制数组
		byte[] readFileToByteArray = FileUtils.readFileToByteArray(new File(path));

		//发送post get put
		resource.put(String.class, readFileToByteArray);
		System.out.println("成功发送");
	}
	@Test
	public void test1(){
		Producter producter = new Producter();
		producter.init();
		producter.sendMessage("qqq","www"+":"+"1234");
		producter.sendMessage("qqq","www"+":"+"1234");
	}
	@Test
	public void test2(){
		Comsumer comsumer = new Comsumer();
		comsumer.init();
		comsumer.getMessage("qqq");
	}



}
