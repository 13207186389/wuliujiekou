package com.pengyou.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


/**
 * 聚合数据查询各大快递公司的编码API
 */
@Component
public class JhsjComApi {

    @Autowired
    RestTemplate restTemplate;

    //配置自己申请key
    private  String APPKEY = "34cef7215ed7d2103af6cdb6b831f382";
    //请求接口地址
    private String url ="http://v.juhe.cn/exp/com";

    public String getCom(){
        String result=null;
        url=url+"?key="+APPKEY;
        ResponseEntity<String> responseEntity =restTemplate.getForEntity(url,String.class);
        result=responseEntity.getBody();

        return result;
    }
}
