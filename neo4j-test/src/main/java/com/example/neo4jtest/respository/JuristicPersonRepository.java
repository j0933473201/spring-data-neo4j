package com.example.neo4jtest.respository;

import java.util.Collection;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import com.example.neo4jtest.nodeEntity.BaseNodeEntity;
import com.example.neo4jtest.nodeEntity.JuristicPerson;

public interface JuristicPersonRepository extends Neo4jRepository<JuristicPerson, Long> {

	public JuristicPerson findByName(@Param("name") String name);
	
	@Query("MATCH m=allshortestPaths((n:NaturalPerson{name:$name1})-[*]-(b:NaturalPerson{name:$name2})) return m")
	public Collection<BaseNodeEntity> graph(@Param("name1")String name1, @Param("name2")String name2);
}
