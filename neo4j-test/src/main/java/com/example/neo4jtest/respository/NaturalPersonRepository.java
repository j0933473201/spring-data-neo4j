package com.example.neo4jtest.respository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import com.example.neo4jtest.nodeEntity.NaturalPerson;

public interface NaturalPersonRepository extends Neo4jRepository<NaturalPerson, Long> {

	public NaturalPerson findByName(@Param("name") String name);
}
