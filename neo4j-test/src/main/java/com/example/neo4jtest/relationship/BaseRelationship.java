package com.example.neo4jtest.relationship;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.StartNode;

import com.example.neo4jtest.nodeEntity.BaseNodeEntity;

/*
 * 可透過
 * 1. @Relationship(type = "xxx", direction = Relationship.xxx)來建立關係
 * 2. 也可透過建立關係物件建立關係,客製化更多,例如屬性的賦予...
 */
public abstract class BaseRelationship {

	@Id
	@GeneratedValue
	private Long id;

	@StartNode
	private BaseNodeEntity startNode;

	@EndNode
	private BaseNodeEntity endNode;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BaseNodeEntity getStartNode() {
		return startNode;
	}

	public void setStartNode(BaseNodeEntity startNode) {
		this.startNode = startNode;
	}

	public BaseNodeEntity getEndNode() {
		return endNode;
	}

	public void setEndNode(BaseNodeEntity endNode) {
		this.endNode = endNode;
	}

}
