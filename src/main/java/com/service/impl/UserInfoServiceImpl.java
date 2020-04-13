//package com.service.impl;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//
//import org.neo4j.driver.v1.Record;
//import org.neo4j.driver.v1.StatementResult;
//import org.neo4j.driver.v1.Transaction;
//import org.neo4j.driver.v1.types.Node;
//import org.neo4j.driver.v1.types.Path;
//import org.neo4j.driver.v1.types.Path.Segment;
//import org.neo4j.driver.v1.types.Relationship;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.alibaba.fastjson.JSONObject;
//import com.bean.CommonResult;
//import com.bean.DNAPathEntity;
//import com.bean.EdgeEntity;
//import com.bean.NodeEntity;
//import com.bean.Sql;
//import com.domain.Assets;
//import com.domain.UserInfo;
//import com.domain.UserInfoRelation;
//import com.driver.Neo4jDriver;
//import com.repository.UserRepository;
//import com.service.IUserInfoService;
//
//@Service
////@Transactional
//public class UserInfoServiceImpl implements IUserInfoService {
//	private static final Logger logger = LoggerFactory.getLogger(UserInfoServiceImpl.class);
//	
//	@Autowired
//	private UserRepository userRepository;
//	
//	@Autowired
//	private Neo4jDriver neo4jDriver;
//	
//	public CommonResult addOne(UserInfo userInfo) {
//		CommonResult result = new CommonResult();
//		try {
//			userRepository.addOne(userInfo);
//		} catch (Exception e) {
//			logger.error(e.toString());
//			result.setState(false);
//			result.setMsg(e.toString());
//		}
//		return result;
//	}
//	
//	public CommonResult queryByName(String name) {
//		CommonResult result = new CommonResult();
//		try {
//			List<UserInfo> userList = userRepository.queryByName(name);
//			result.setData(userList);
//		} catch (Exception e) {
//			logger.error(e.toString());
//			result.setState(false);
//			result.setMsg(e.toString());
//		}
//		return result;
//	}
//	
//	public CommonResult toJavaObject(JSONObject info) {
//		CommonResult result = new CommonResult();
//		try {
//			Sql sql = JSONObject.toJavaObject(info, Sql.class);
//			result.setData(sql);
//		} catch (Exception e) {
//			logger.error(e.toString());
//			result.setState(false);
//			result.setMsg(e.toString());
//		}
//		return result;
//	}
//
//	public CommonResult addBatchUserInfo(List<UserInfo> userList) {
//		CommonResult result = new CommonResult();
//		try {
//			userRepository.addBatch_UserInfo(userList);
//		} catch (Exception e) {
//			logger.error(e.toString());
//			result.setState(false);
//			result.setMsg(e.toString());
//		}
//		return result;
//	}
//	
//	public CommonResult addBatchAssets(List<Assets> assetsList) {
//		CommonResult result = new CommonResult();
//		try {
//			userRepository.addBatch_Assets(assetsList);
//		} catch (Exception e) {
//			logger.error(e.toString());
//			result.setState(false);
//			result.setMsg(e.toString());
//		}
//		return result;
//	}
//	
//	public CommonResult addBatchUserInfoRelation(List<UserInfoRelation<UserInfo,Assets>> userRelList) {
//		CommonResult result = new CommonResult();
//		try {
//			userRepository.addBatch_UserInfoRelation(userRelList);
//		} catch (Exception e) {
//			logger.error(e.toString());
//			result.setState(false);
//			result.setMsg(e.toString());
//		}
//		return result;
//	}
//	
//	public CommonResult getPath(String name, String accountNum) {
//		CommonResult result = new CommonResult();
//		DNAPathEntity dnaPathEntity = new DNAPathEntity();
//		List<Node> nodeList = new ArrayList<Node>();
//		List<Relationship> relList = new ArrayList<Relationship>();
//		
////		Session session = null;
////		Transaction ts = null;
//		try {
////			Driver driver = neo4jDriver.getDriver();
////			session = driver.session();
////			ts = session.beginTransaction();
//			Transaction ts = neo4jDriver.getTranscation();
//			Map<String, Object> params = new HashMap<String, Object>();
//			params.put("name", name);
//			params.put("accountNum", accountNum);
//			String pathCql = "match path=(startNode:userInfo_nd{name:{name}})-[*2..2]"
//					+ "->(endNode:money_nd{accountNum:{accountNum}}) return path";
////			String StringCql = "match path=(startNode:userInfo_nd{name:{name}})-[:user_assets_line]"
////					+ "->(endNode:asserts_nd{accountNum:{accountNum}}) return path";
//			StatementResult statementResult = ts.run(pathCql, params);
//			Node startNode = getStartNode(ts,params);
//			Node endNode = getEndNode(ts,params);
//			handleDNAPathData(statementResult,nodeList,relList);
//			List<NodeEntity> nodeEntityList = handleNodeList(nodeList,startNode,endNode);
//			List<EdgeEntity> edgeEntityList = handleRelList(relList);
//			dnaPathEntity.setNodeList(nodeEntityList);
//			dnaPathEntity.setEdgeList(edgeEntityList);
//			result.setData(dnaPathEntity);
//			ts.success();
//		} catch (Exception e) {
//			logger.error(e.toString());
//			result.setState(false);
//			result.setMsg(e.toString());
//		}finally {
//			neo4jDriver.close();
////			if (null != ts) {
////	            ts.close();
////	        }
////	        if (null != session) {
////	            session.close();
////	        }
//		}
//		return result;
//	}
//	
//	public Node getStartNode(Transaction ts, Map<String, Object> params) {
//		String cql = "match path=(startNode:userInfo_nd{name:{name}})-[*2..2]->(:money_nd{accountNum:{accountNum}}) "
//				+ "where not ()-[*]->(startNode) "
//				+ "return startNode";
//		StatementResult statementResult = ts.run(cql, params);
//		Record record = statementResult.next();
//		Node node = record.get("startNode").asNode();
//		return node;
//	}
//	
//	public Node getEndNode(Transaction ts, Map<String, Object> params) {
//		String cql = "match (endNode:money_nd{accountNum:{accountNum}}) return endNode";
//		StatementResult statementResult = ts.run(cql, params);
//		Record record = statementResult.next();
//		Node node = record.get("endNode").asNode();
//		return node;
//	}
//	
//	public void handleDNAPathData(StatementResult statementResult, List<Node> nodeList, 
//			List<Relationship> relList) {
//		while(statementResult.hasNext()) {
//			Record record = statementResult.next();
////			Node startNode = record.get("startNode").asNode();
//			Path path = record.get("path").asPath();
//			Iterator<Segment> iterator = path.iterator();
//			while(iterator.hasNext()) {
//				Segment segment = iterator.next();
//				nodeList.add(segment.start());
//				nodeList.add(segment.end());
//				relList.add(segment.relationship());
//			}
//		}
//	}
//	
//	public List<NodeEntity> handleNodeList(List<Node> nodeList, Node startNode,
//			Node endNode) {
//		List<NodeEntity> nodeEntityList = new ArrayList<NodeEntity>();	
//		List<Long> nodeIdList = new ArrayList<Long>();
//		if(nodeList != null && nodeList.size() > 0) {
//			for(int i = 0; i < nodeList.size(); i++) {
//				Node node = nodeList.get(i);
//				if(!nodeIdList.contains(node.id())) {
//					NodeEntity nodeEntity = new NodeEntity();
//					nodeEntity.setId(node.id());
//					Iterator<String> labelsIterator = node.labels().iterator();
//					String label = null;
//					while(labelsIterator.hasNext()) {
//						label = labelsIterator.next();
//					}
//					if(startNode != null && endNode != null) {
//						if(startNode.equals(node)) {
//							nodeEntity.setSubLabel("startNode");
//						}else if(endNode.equals(node)) {
//							nodeEntity.setSubLabel("endNode");
//						}else {
//							nodeEntity.setSubLabel("MiddleNode");
//						}
//					}
//					nodeEntity.setLabel(label);
//					nodeEntity.setProperty(node.asMap());
//					
//					nodeEntityList.add(nodeEntity);
//					nodeIdList.add(node.id());
//				}
//			}
//		}
//		return nodeEntityList;
//	}
//	
//	public List<EdgeEntity> handleRelList(List<Relationship> relList) {
//		List<EdgeEntity> relEntityList = new ArrayList<EdgeEntity>();
//		List<Long> edgeIdList = new ArrayList<Long>();
//		if(relList != null && relList.size() > 0) {
//			for(int i = 0; i < relList.size(); i++) {
//				Relationship rel = relList.get(i);
//				if(!edgeIdList.contains(rel.id())) {
//					EdgeEntity edgeEntity = new EdgeEntity();
//					edgeEntity.setId(rel.id());
//					edgeEntity.setStartId(rel.startNodeId());
//					edgeEntity.setEndId(rel.endNodeId());
//					edgeEntity.setType(rel.type());
//					edgeEntity.setProperty(rel.asMap());
//					
//					relEntityList.add(edgeEntity);
//					edgeIdList.add(rel.id());
//				}
//			}
//		}
//		return relEntityList;
//	}
//
//}
