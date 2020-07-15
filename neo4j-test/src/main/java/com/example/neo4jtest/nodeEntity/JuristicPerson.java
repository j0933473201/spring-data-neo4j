package com.example.neo4jtest.nodeEntity;

import java.util.Set;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

//法人
@NodeEntity
public class JuristicPerson extends BaseNodeEntity {

	// 建立負責人關係
//	(自然人)-[r:Responsible]->(法人)
	@Relationship(type = "responsible", direction = Relationship.INCOMING)
	private NaturalPerson responPerson;

	// 建立持股關係
	// (自然人)-[r:shareholdings{persentage:45}]->(法人)
	@Relationship(type = "shareholdings", direction = Relationship.INCOMING)
	private Set<NaturalPerson> holdingsPerson;

	// 建立監察人關係
	// (自然人)-[r:Supervisor]->(法人)
	@Relationship(type = "Supervisor", direction = Relationship.INCOMING)
	private NaturalPerson supervisor;

	public NaturalPerson getResponPerson() {
		return responPerson;
	}

	public void setResponPerson(NaturalPerson responPerson) {
		this.responPerson = responPerson;
	}

	public Set<NaturalPerson> getHoldingsPerson() {
		return holdingsPerson;
	}

	public void setHoldingsPerson(Set<NaturalPerson> holdingsPerson) {
		this.holdingsPerson = holdingsPerson;
	}

	public NaturalPerson getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(NaturalPerson supervisor) {
		this.supervisor = supervisor;
	}


}
