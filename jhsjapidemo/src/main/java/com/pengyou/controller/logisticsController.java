package com.pengyou.controller;

import com.pengyou.api.AliyunApi;
import com.pengyou.api.JhsjComApi;
import com.pengyou.api.JhsjApi;
import com.pengyou.api.KdnApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class logisticsController {

    @Autowired
    private JhsjApi jhsjApi;

    @Autowired
    private AliyunApi aliyunApi;

    @Autowired
    private KdnApi kdnApi;

    @Autowired
    private JhsjComApi jhsjComApi;

    @GetMapping(value = "/jhshget")
    public String jhshget(){

        //顺丰
        //String com = "sf";
        //String no = "SF1001457859475";

        //圆通
        String com = "yt";
        String no = "806358038879451371";


        String senderPhone = "";
        String receiverPhone = "";
        String result="服务器异常";
        try {
            result=jhsjApi.getRequest(com,no,senderPhone,receiverPhone);
            System.out.println(result);

        }catch (Exception e){
            e.printStackTrace();
        }
        return result ;
    }

    @GetMapping(value = "/aliyunget")
    public String aliyunget(){
        //顺丰
        //String no = "SF1001457859475";
        //可以不用写
        //String type = "SFEXPRESS";

        //圆通
        //String no = "806358038879451371";
        //String type = "";

        //顺丰
        String no = "363957600636";
        String type = "";


        String result="服务器异常";
        try {
            result=aliyunApi.getRequest(no,type);
            System.out.println(result);

        }catch (Exception e){
            e.printStackTrace();
        }
        return result ;
    }

    @GetMapping(value = "/kdnget")
    public String kdnget(){
        String result="服务器异常";
        try {
            //安能物流
            //result = kdnApi.getRequest("ANE", "210001633606");
            //顺丰物流  快递鸟查不了顺丰物流的信息
            //result = kdnApi.getRequest("SF", "SF1001457859475");
            //圆通速递
            result = kdnApi.getRequest("YTO", "806358038879451371");

        }catch (Exception e){
            e.printStackTrace();
        }

        return result ;
    }

    @GetMapping(value = "getCom")
    public String getCom(){
        String result="服务器异常";
        try {
            result=jhsjComApi.getCom();
            System.out.println(result);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

}
