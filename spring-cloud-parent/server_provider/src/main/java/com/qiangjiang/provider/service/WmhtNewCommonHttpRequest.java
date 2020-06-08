package com.qiangjiang.provider.service;

//import brave.http.HttpTracing;

import com.google.gson.Gson;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Optional;

//import com.hualala.core.utils.SystemUtils;

@Service
public class WmhtNewCommonHttpRequest {

    private Logger logger = LoggerFactory.getLogger(WmhtNewCommonHttpRequest.class);
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
//    @Autowired
//    private HttpTracing httpTracing;

    private  int connectTimeout = 15000;
    private  int socketTimeout = 15000;

    private String ENCODE = "UTF-8";
    public static String ALARM_KEY = "hualala-be-pay:exception:alarm";


    public Optional postJSON(String url, Object param, String bankOrderNo ){
        long startTime = System.currentTimeMillis();
        String hostName = "";
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        HttpPost httpPost = new HttpPost(url);
        try{
//            httpPost.addHeader(HTTP.CONTENT_TYPE, "text/xml");
            //解决XStream对出现双下划线的bug
            XStream xStreamForRequestPostData = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("-_", "_")));
            //将要提交给API的数据对象转换成XML格式数据Post给API
            String postDataXMLOld = xStreamForRequestPostData.toXML(param);
            String postDataXML = postDataXMLOld.replace("&quot;", "\"");
            logger.info("url [ "+url+" ] bankOrderNo ["+bankOrderNo+"] reqDdata ["+new Gson().toJson(param) +"]");
            if (param != null && !"".equals(param)) {
                StringEntity reqEntity = new StringEntity(postDataXMLOld, ContentType.create("text/plain", ENCODE));
                reqEntity.setContentType("text/xml");
                reqEntity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "text/xml"));
                httpPost.setEntity(reqEntity);
            }
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
            httpPost.setConfig(requestConfig);
            httpClient = HttpClients.createDefault();
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity, "UTF-8");
                logger.info("url [ "+url+" ] bankOrderNo ["+bankOrderNo+" ] resDdata ["+result+"]");
                //对相应进行验签

                return Optional.ofNullable(result);
            }
        }catch (ConnectionPoolTimeoutException e) {
            e.printStackTrace();
            logger.error("connectPool timeout " + httpPost.getRequestLine());
            redisTemplate.opsForList().leftPush(ALARM_KEY, "url [" + url + "] bankOrderNo [" + bankOrderNo + "] connectPool timeout"+" hostName ["+hostName+"]");
        } catch (ConnectTimeoutException e) {
            e.printStackTrace();
            logger.error("connect timeout " + httpPost.getRequestLine());
            redisTemplate.opsForList().leftPush(ALARM_KEY, "url [" + url + "] bankOrderNo [" + bankOrderNo + "] connect timeout"+" hostName ["+hostName+"]");
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            logger.error("socket timeout " + httpPost.getRequestLine());
            redisTemplate.opsForList().leftPush(ALARM_KEY, "url [" + url + "] bankOrderNo [" + bankOrderNo + "] socket timeout"+" hostName ["+hostName+"]");
        } catch(UnknownHostException e){
            e.printStackTrace();
            logger.error("UnknownHostException " + httpPost.getRequestLine());
            redisTemplate.opsForList().leftPush(ALARM_KEY, "url [" + url + "] bankOrderNo [" + bankOrderNo + "] UnknownHostException"+" hostName ["+hostName+"]");
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("exception " + httpPost.getRequestLine(),e);
            redisTemplate.opsForList().leftPush(ALARM_KEY, "url [" + url + "] bankOrderNo [" + bankOrderNo + "] exception"+" hostName ["+hostName+"]");
        }finally{
            if (logger.isInfoEnabled()) {
                logger.info("url [" + url + "] bankOrderNo [" + bankOrderNo + "] cost [" + (System.currentTimeMillis() - startTime)  + "]ms");
            }
            httpPost.abort();
        }
        return Optional.empty();
    }


    public  String executeGetNew(String url, String characterSet) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet httpget = new HttpGet(url);
            CloseableHttpResponse resp = httpclient.execute(httpget);
            return EntityUtils.toString(resp.getEntity(), characterSet);
        } catch (Exception e) {
            logger.error("Failed to connect to url ", e);
            System.out.println("Failed to connect to url "+ e);
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
            }
        }
        return "";

    }

}
