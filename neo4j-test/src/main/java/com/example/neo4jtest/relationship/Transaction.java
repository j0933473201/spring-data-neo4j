package com.example.neo4jtest.relationship;

import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.RelationshipEntity;

@RelationshipEntity(type = "Transaction")
public class Transaction extends BaseRelationship {

	@Property
	private Integer percentage;

	public Integer getPercentage() {
		return percentage;
	}

	public void setPercentage(Integer percentage) {
		this.percentage = percentage;
	}

}
