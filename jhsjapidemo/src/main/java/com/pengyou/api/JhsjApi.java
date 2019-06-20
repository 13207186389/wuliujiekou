package com.pengyou.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 聚合数据查询物流API
 * 官网 : https://www.juhe.cn/docs/api/id/43
 */
@Component
public class JhsjApi {

    @Autowired
    RestTemplate restTemplate;

    //配置自己申请key
    private  String APPKEY = "34cef7215ed7d2103af6cdb6b831f382";
    //请求接口地址
    private String url ="http://v.juhe.cn/exp/index";

    public  String getRequest(String com,String no,String senderPhone,String receiverPhone){
        String result = null;
        //请求参数
        Map params = new HashMap();
        //需要查询的快递公司编号
        params.put("com",com);
        //需要查询的订单号
        params.put("no",no);
        //寄件人手机号后四位 顺丰的需要填其中一个
        params.put("senderPhone",senderPhone);
        //收件人手机号后四位 顺丰需要填其中一个
        params.put("receiverPhone",receiverPhone);
        //应用APPKEY(应用详细页查询)
        params.put("key",APPKEY);
        //返回数据的格式,xml或json，默认json
        params.put("dtype","");

        url=url+"?"+urlencode(params);
        ResponseEntity<String> responseEntity =restTemplate.getForEntity(url,String.class);
        result=responseEntity.getBody();

        return result;
    }


    //将map型转为请求参数型
    public static String urlencode(Map<String,Object>data) {
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
