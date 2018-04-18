package com.xmy.portal.service;

import com.xmy.portal.utils.JsonResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description:
 * @Author: xumengyang
 * @Date: Created in 15:55 2018/4/11
 */
@FeignClient(value = "tour-service")
@Service("chatService")
public interface ChatService {

    @RequestMapping("/saveChatLog")
    JsonResponse saveChatLog(@RequestParam("fromId") String fromId, @RequestParam("toId") String toId,@RequestParam("content")String content, @RequestParam("state") String state);
}
