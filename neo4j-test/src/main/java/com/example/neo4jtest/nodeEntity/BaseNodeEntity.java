package com.example.neo4jtest.nodeEntity;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.Property;

public abstract class BaseNodeEntity {

	@Id
	@GeneratedValue
	private Long id;
	
	@Property
	private String name;

	public BaseNodeEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BaseNodeEntity(String name) {
		super();
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
