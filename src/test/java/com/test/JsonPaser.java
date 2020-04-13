package com.test;

import com.alibaba.fastjson.JSON;
import com.domain.UserInfo;

import net.sf.json.JSONObject;

public class JsonPaser {
	private static String jsonStr = "{\"id\":11,\"name\":\"zhangshan\", \"age\": 10, \"sex\": \"男\"}";  
    
    public static void main(String[] args) {  
          
        //JSON-LIB  
    	JSONObject jsonResult = JSONObject.fromObject(jsonStr);  
        UserInfo userInfo = (UserInfo)JSONObject.toBean(jsonResult, UserInfo.class);  
        System.out.println("userInfo:" + userInfo.toString());  
          
        //fastjson  
        com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(jsonStr);  
        UserInfo userInfo2 = com.alibaba.fastjson.JSONObject.toJavaObject(jsonObject, UserInfo.class);  
        System.out.println("userInfo2:" + userInfo2.toString());  
    }  
}
