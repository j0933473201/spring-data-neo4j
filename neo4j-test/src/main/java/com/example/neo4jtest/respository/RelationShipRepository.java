package com.example.neo4jtest.respository;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.example.neo4jtest.relationship.BaseRelationship;

public interface RelationShipRepository extends Neo4jRepository<BaseRelationship, Long> {

}
