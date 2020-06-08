package com.qiangjiang.provider.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.qiangjiang.provider.utils.EmailUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    private static final Logger logger = LoggerFactory.getLogger(EmailController.class);

    @RequestMapping("sendEmail")
    public String sendEmail() throws JsonProcessingException {
        System.out.println("发送邮件开始");
        String[] toUsers = new String[]{"jiangqiang@hualala.com","1015678395@qq.com"};
        boolean isSend = EmailUtils.sendEmail("这是一封测试邮件", toUsers, null, "<h3><a href='http://www.baidu.com'>百度一下，你就知道</a></h3>", null);
        return "发送邮件:" + isSend;
    }
}
