package com.qiangjiang.provider.controller;

import com.qiangjiang.provider.service.WmhtNewCommonHttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("provider")
public class ProviderController {

    @Autowired
    private WmhtNewCommonHttpRequest wmhtNewCommonHttpRequest;

    @RequestMapping("get")
    public String getData(){
        return "服务提供方";
    }



    @RequestMapping("weather")
    public String getWeather(){
        String cityname = "亳州";
        String dtype = "json";
        int format = 2;
        String key = "28c172a37761bdc6c1e0c8a4e376b4c5";
        String url = "http://v.juhe.cn/weather/index?format="+format+"&cityname="+cityname+"&key="+key;
        String resData = wmhtNewCommonHttpRequest.executeGetNew(url, null);
        return resData;
    }

    @RequestMapping("getZuowen")
    public String getZuowen(@RequestParam int id){
        System.out.println("getZuowen start ....."+id);
        String key = "61c0ab30eb75ed1de2f6a694206e1956";
        String url = "http://zuowen.api.juhe.cn/zuowen/typeList?key="+key+"&id="+id;
        String resData = wmhtNewCommonHttpRequest.executeGetNew(url, null);
        return resData;
    }
}
