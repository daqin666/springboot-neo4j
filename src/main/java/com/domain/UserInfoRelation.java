package com.domain;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type="user_assets_line")
public class UserInfoRelation<S extends BaseNode,E extends BaseNode> {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@StartNode
	private S startNode;
	
	@EndNode
	private E endNode;

	public UserInfoRelation() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public S getStartNode() {
		return startNode;
	}

	public void setStartNode(S startNode) {
		this.startNode = startNode;
	}

	public E getEndNode() {
		return endNode;
	}

	public void setEndNode(E endNode) {
		this.endNode = endNode;
	}

}
