package com.xmy.portal;

import com.xmy.portal.socket.WebSocketController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class TourPortalApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(TourPortalApplication.class, args);
        WebSocketController.setApplicationContext(run);
	}
}
