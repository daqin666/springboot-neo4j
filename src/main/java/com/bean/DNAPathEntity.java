package com.bean;

import java.util.List;

public class DNAPathEntity {
	private List<NodeEntity> nodeList;
	
	private List<EdgeEntity> edgeList;

	public DNAPathEntity() {
		super();
	}

	public List<NodeEntity> getNodeList() {
		return nodeList;
	}

	public void setNodeList(List<NodeEntity> nodeList) {
		this.nodeList = nodeList;
	}

	public List<EdgeEntity> getEdgeList() {
		return edgeList;
	}

	public void setEdgeList(List<EdgeEntity> edgeList) {
		this.edgeList = edgeList;
	}
	
}
