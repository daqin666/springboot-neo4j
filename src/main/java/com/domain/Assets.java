package com.domain;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity(label="asserts_nd")
public class Assets extends BaseNode{
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String accountNum;

	public Assets() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}

	@Override
	public String toString() {
		return "Assets [id=" + id + ", accountNum=" + accountNum + "]";
	}
	
}
