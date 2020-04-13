//package com.service;
//
//import java.util.List;
//import java.util.Map;
//
//import org.neo4j.driver.v1.StatementResult;
//import org.neo4j.driver.v1.Transaction;
//import org.neo4j.driver.v1.types.Node;
//import org.neo4j.driver.v1.types.Relationship;
//
//import com.alibaba.fastjson.JSONObject;
//import com.bean.CommonResult;
//import com.bean.EdgeEntity;
//import com.bean.NodeEntity;
//import com.domain.Assets;
//import com.domain.UserInfo;
//import com.domain.UserInfoRelation;
//
////@Component
//public interface IUserInfoService {
//	
//	public CommonResult addOne(UserInfo userInfo);
//	
//	public CommonResult queryByName(String name);
//	
//	public CommonResult toJavaObject(JSONObject info);
//	
//	public CommonResult addBatchUserInfo(List<UserInfo> userList);
//	
//	public CommonResult addBatchAssets(List<Assets> assetsList);
//	
//	public CommonResult addBatchUserInfoRelation(List<UserInfoRelation<UserInfo,Assets>> userRelList);
//	
//	public CommonResult getPath(String name, String accountNum);
//	
//	public Node getStartNode(Transaction ts, Map<String, Object> params);
//	
//	public Node getEndNode(Transaction ts, Map<String, Object> params);
//	
//	public void handleDNAPathData(StatementResult statementResult, List<Node> nodeList, 
//			List<Relationship> relList);
//	
//	public List<NodeEntity> handleNodeList(List<Node> nodeList, Node startNode,
//			Node endNode);
//	
//	public List<EdgeEntity> handleRelList(List<Relationship> relList);
//	
//}
