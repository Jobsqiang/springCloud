package com.qiangjiang.provider.controller;

import com.google.gson.Gson;
import com.qiangjiang.provider.model.User;
import com.qiangjiang.provider.service.SendMessageService;
import com.qiangjiang.provider.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Api(description = "用户管理")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private SendMessageService sendMessageService;

    @RequestMapping("add")
    public void addUser(){

        User user = new User();
        user.setPassWord("12345");
        user.setToken(UUID.randomUUID().toString());
        user.setUserName("系统");
        User user1 = userService.add(user);
        System.out.println("user:"+user1);
        sendMessageService.userMessage(user1);
        System.out.println("user:"+new Gson().toJson(user1));

    }

    @ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Integer", paramType = "path")
    @RequestMapping(value = "user/{id}", method = RequestMethod.GET)
    public void queryUser(){
        System.out.println("hello swagger!!!");
    }
}
