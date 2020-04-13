package com.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bean.CommonResult;
import com.domain.Assets;
import com.domain.UserInfo;
import com.domain.UserInfoRelation;
import com.service.UserInfoService;

@RestController
public class HelloController {
	
//	@Autowired
//	private IUserInfoService userInfoService;
	
	@Autowired
	private UserInfoService userInfoService;
	
	
	@RequestMapping("/hello")
	public String hello() {
		return "hello Spring Boot";
	}

	@RequestMapping("/hello1")
	public JSONObject hello1() {
		JSONObject temp = new JSONObject();
		temp.put("hello1", "hello Spring Boot");
		return temp;
	}
	
	@RequestMapping(value="/hello2", method=RequestMethod.POST)
	public JSONObject hello2(@RequestBody JSONObject info) {
		JSONObject temp = new JSONObject();
		temp.put("hello2", info.getString("hello2"));
		return temp;
	}
	
	@RequestMapping(value="/addOne", method=RequestMethod.POST)
	public CommonResult addOne(@RequestBody JSONObject info) {
		UserInfo userInfo = new UserInfo();
		userInfo.setName(info.getString("name"));
		userInfo.setAge(info.getInteger("age"));
		userInfo.setSex(info.getString("sex"));
		CommonResult result = userInfoService.addOne(userInfo);
		return result;
	}
	
	@RequestMapping(value="/queryByName", method=RequestMethod.POST)
	public CommonResult queryByName(@RequestBody JSONObject info) {
		CommonResult result = userInfoService.queryByName(info.getString("name"));
		return result;
	}
	
	@RequestMapping(value="/toJavaObject", method=RequestMethod.POST)
	public CommonResult toJavaObject(@RequestBody JSONObject info) {
		CommonResult result = userInfoService.toJavaObject(info);
		return result;
	}
	
	@RequestMapping(value="/addBatchUserInfo", method=RequestMethod.POST)
	public CommonResult addBatchUserInfo(@RequestBody JSONArray infoArray) {
		List<UserInfo> userList = new ArrayList<UserInfo>();
		if(infoArray != null && infoArray.size() > 0) {
			for(int i = 0; i < infoArray.size(); i++) {
				JSONObject info = infoArray.getJSONObject(i);
				UserInfo user = new UserInfo();
				user.setName(info.getString("name"));
				user.setAge(info.getInteger("age"));
				user.setSex(info.getString("sex"));
				userList.add(user);
			}
		}
		CommonResult result = userInfoService.addBatchUserInfo(userList);
		return result;
	}
	
	@RequestMapping(value="/addBatchAssets", method=RequestMethod.POST)
	public CommonResult addBatchAssets(@RequestBody JSONArray infoArray) {
		List<Assets> assetsList = new ArrayList<Assets>();
		if(infoArray != null && infoArray.size() > 0) {
			for(int i = 0; i < infoArray.size(); i++) {
				JSONObject info = infoArray.getJSONObject(i);
				Assets assets = new Assets();
				assets.setAccountNum(info.getString("accountNum"));
				assetsList.add(assets);
			}
		}
		CommonResult result = userInfoService.addBatchAssets(assetsList);
		return result;
	}
	
	@RequestMapping(value="/addBatchUserInfoRelation", method=RequestMethod.POST)
	public CommonResult addBatchUserInfoRelation(@RequestBody JSONArray infoArray) {
		List<UserInfoRelation<UserInfo,Assets>> userInfoRelList = new ArrayList<UserInfoRelation<UserInfo,Assets>>();
		if(infoArray != null && infoArray.size() > 0) {
			for(int i = 0; i < infoArray.size(); i++) {
				JSONObject info = infoArray.getJSONObject(i);
				Assets assets = new Assets();
				assets.setAccountNum(info.getString("accountNum"));
				
				UserInfo user = new UserInfo();
				user.setName(info.getString("name"));
				user.setAge(info.getInteger("age"));
				user.setSex(info.getString("sex"));
				
				UserInfoRelation<UserInfo,Assets> userInfoRel = new UserInfoRelation<UserInfo,Assets>();
				userInfoRel.setStartNode(user);
				userInfoRel.setEndNode(assets);
				
				userInfoRelList.add(userInfoRel);
			}
		}
		CommonResult result = userInfoService.addBatchUserInfoRelation(userInfoRelList);
		return result;
	}
	
	@RequestMapping(value="/getPath", method=RequestMethod.POST)
	public CommonResult getPath(@RequestBody JSONObject info) {
		String name = info.getString("name");
		String accountNum = info.getString("accountNum");
		CommonResult result = userInfoService.getPath(name, accountNum);
		return result;
	}
	
}
