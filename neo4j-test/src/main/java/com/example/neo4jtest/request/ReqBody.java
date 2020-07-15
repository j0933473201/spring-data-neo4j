package com.example.neo4jtest.request;

import com.example.neo4jtest.nodeEntity.NaturalPerson;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ReqBody {

	@JsonProperty("naturalPerson")
	private NaturalPerson naturalPerson;

	private String name;

	public NaturalPerson getNaturalPerson() {
		return naturalPerson;
	}

	public void setNaturalPerson(NaturalPerson naturalPerson) {
		this.naturalPerson = naturalPerson;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
