package com.example.neo4jtest.relationship;

import org.neo4j.ogm.annotation.RelationshipEntity;

@RelationshipEntity(type = "Customer")
public class Customer extends BaseRelationship {

}
