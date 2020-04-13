package com.bean;

import java.util.Map;

public class NodeEntity {
	private Long id;
	
	private String label;
	
	private Map<String, Object> property;
	
	private String subLabel;

	public NodeEntity() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Map<String, Object> getProperty() {
		return property;
	}

	public void setProperty(Map<String, Object> property) {
		this.property = property;
	}

	public String getSubLabel() {
		return subLabel;
	}

	public void setSubLabel(String subLabel) {
		this.subLabel = subLabel;
	}

}
