package com.pengyou.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 *阿里云查询物流API
 * 官网 : https://market.aliyun.com/products/56928004/cmapi021863.html?spm=5176.2020520132.101.26.GSZWuX#sku=yuncode1586300000
 */
@Component
public class AliyunApi {

    @Autowired
    private RestTemplate restTemplate;

    //请求接口地址
    private String url = "http://wuliu.market.alicloudapi.com/kdi";
    //替换这里填写你自己的AppCode 请在买家中心查看
    private String appcode = "aa7f34d0703d4c8aa47e4de0f3386733";

    /**
     *
     * @param no 快递单号 【顺丰请输入单号 : 收件人或寄件人手机号后四位。例如：123456789:1234】
     * @param type 快递公司字母简写：不知道可不填 95%能自动识别，填写查询速度会更快【见产品详情】
     * @return
     */
    public String getRequest(String no,String type){
        //定义头信息
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Authorization", "APPCODE " + appcode);
        HttpEntity<String> requestEntity = new HttpEntity<String>(null, requestHeaders);
        //定义参数信息
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("no",no);
        params.put("type",type);
        url=url+"?"+urlencode(params);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET,requestEntity,String.class);
        String result=response.getBody();

        return result;
    }

    //将map型转为请求参数型
    public static String urlencode(Map<String,Object> data) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry i : data.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue()+"","UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

}
