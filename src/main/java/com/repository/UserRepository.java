package com.repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.domain.Assets;
import com.domain.UserInfo;
import com.domain.UserInfoRelation;

//@Component
@Repository
public interface UserRepository extends Neo4jRepository<UserInfo, Long>{
	
	//增
	@Query("create (u:userInfo_nd{name:{info}.name, age:{info}.age, sex:{info}.sex }) return u")
	void addOne(@Param("info") UserInfo userInfo);

	@Query("with {userList} as batch "
			+ "unwind batch as row "
			+ "merge (u:userInfo_nd{name:row.name}) "
			+ "on match set u.sex = row.sex, u.age = row.age "
			+ "on create set u.sex = row.sex, u.age = row.age ")
	void addBatch_UserInfo(@Param("userList") List<UserInfo> userList);
	
	@Query("with {assetsList} as batch "
			+ "unwind batch as row "
			+ "merge (a:asserts_nd{accountNum:row.accountNum}) "
			+ "on match set a.accountNum = row.accountNum "
			+ "on create set a.accountNum = row.accountNum ")
	void addBatch_Assets(@Param("assetsList") List<Assets> assetsList);
	
	@Query("with {userRelList} as batch "
			+ "unwind batch as row "
			+ "match (out:userInfo_nd{name:row.startNode.name}),(in:asserts_nd{accountNum:row.endNode.accountNum}) "
			+ "merge (out)-[:user_assets_line]->(in) ")
	void addBatch_UserInfoRelation(@Param("userRelList") List<UserInfoRelation<UserInfo,Assets>> userRelList);
	
	//查
	@Query("match (u:userInfo_nd{name:{name}}) return u")
	List<UserInfo> queryByName(@Param("name") String userName);
	
}
