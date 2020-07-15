package com.example.neo4jtest.respository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import com.example.neo4jtest.nodeEntity.JuristicPerson;

public interface JuristicPersonRepository extends Neo4jRepository<JuristicPerson, Long> {

	public JuristicPerson findByName(@Param("name") String name);
}
