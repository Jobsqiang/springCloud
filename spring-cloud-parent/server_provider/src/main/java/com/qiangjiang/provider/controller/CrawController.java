package com.qiangjiang.provider.controller;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CrawController {

    @RequestMapping("crawTest")
    public void crawTest(){
        try {

            Document document = Jsoup.connect("http://blog.csdn.net/qq_30581017")
                    .userAgent("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)")
                    .get();

            Elements elements = document.getAllElements().first().children();
            Element element = elements.get(0);
            System.out.println("element:"+element);


//            //包含所有列表的文章
//            Elements elements = document.getElementsByClass("list").first().children();
//            for (Element element : elements) {
//                //link_title下元素所有的元素
//                Elements list_item = element.getElementsByClass("link_title").first().children();
//
//                for (Element elementEach : list_item) {
//
//                    String path = elementEach.attr("href");
//
//                    String text = elementEach.html();
//
//                    System.out.println(path + " -> " + text);
//                }
//            }




        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
