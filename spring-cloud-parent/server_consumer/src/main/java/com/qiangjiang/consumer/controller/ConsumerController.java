package com.qiangjiang.consumer.controller;

import com.qiangjiang.consumer.config.CommonProps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("consumer")
public class ConsumerController {

    @Autowired
    private CommonProps commonProps;

    @RequestMapping("get")
    public String get(){
        RestTemplate template = new RestTemplate();
        String data = template.getForObject(commonProps.getTest(), String.class);
        return data;
    }


}
