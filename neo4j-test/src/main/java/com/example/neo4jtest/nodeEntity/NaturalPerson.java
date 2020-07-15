package com.example.neo4jtest.nodeEntity;

import org.neo4j.ogm.annotation.NodeEntity;

//自然人
@NodeEntity
public class NaturalPerson extends BaseNodeEntity {

	public NaturalPerson() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NaturalPerson(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "NaturalPerson [getId()=" + getId() + ", getName()=" + getName() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
}
