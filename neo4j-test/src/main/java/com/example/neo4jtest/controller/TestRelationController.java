package com.example.neo4jtest.controller;

import org.neo4j.driver.internal.shaded.io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.neo4jtest.nodeEntity.JuristicPerson;
import com.example.neo4jtest.nodeEntity.NaturalPerson;
import com.example.neo4jtest.relationship.Customer;
import com.example.neo4jtest.relationship.Responsible;
import com.example.neo4jtest.relationship.Shareholdings;
import com.example.neo4jtest.relationship.Supervisor;
import com.example.neo4jtest.relationship.Transaction;
import com.example.neo4jtest.request.buildRelation.RelationResponsible;
import com.example.neo4jtest.respository.JuristicPersonRepository;
import com.example.neo4jtest.respository.NaturalPersonRepository;
import com.example.neo4jtest.respository.RelationShipRepository;

@RestController
@RequestMapping("/")
public class TestRelationController {

	@Autowired
	private JuristicPersonRepository jpry;

	@Autowired
	private NaturalPersonRepository npry;

	@Autowired
	private RelationShipRepository rsrsy;

	/*
	 * 透過傳入需要建立的關係以及startNode和endNode,先查找是否有創建node,有的話就建立關聯
	 * http://localhost:8080/neo4j/buildRelationship 
	 * 
	 * {
	 *  "startNodeName":"TSXX 股份有限公司", 
	 *  "endNodeName":"TSYY 投資有限公司",
	 *  "relationType":"shareholdings",
	 *  "percentage":25 
	 *  }
	 * 
	 */
	@PostMapping(path = "buildRelationship")
	public Object buildResponsible(@RequestBody RelationResponsible rrbl) {

		return buildRelation(rrbl);

	}

	private Object buildRelation(RelationResponsible rrbl) {
		String type = rrbl.getRelationType();
		Object result = null;

		NaturalPerson npStart = npry.findByName(rrbl.getStartNodeName());
		NaturalPerson npEnd = npry.findByName(rrbl.getEndNodeName());

		JuristicPerson jpStart = jpry.findByName(rrbl.getStartNodeName());
		JuristicPerson jpEnd = jpry.findByName(rrbl.getEndNodeName());

		if (!StringUtil.isNullOrEmpty(type)) {

			switch (type) {
			case "Responsible":

				Responsible rb = new Responsible();

				rb.setStartNode(npStart);
				rb.setEndNode(jpEnd);

				result = rsrsy.save(rb);

				break;
			case "shareholdings":
				Shareholdings shs = new Shareholdings();

				if (npStart == null) {

					shs.setStartNode(jpStart);

				} else {
					shs.setStartNode(npStart);
				}
				shs.setEndNode(jpEnd);
				shs.setPercentage(rrbl.getPercentage());
				result = rsrsy.save(shs);
				break;
			case "Supervisor":
				Supervisor sp = new Supervisor();

				sp.setStartNode(npStart);
				sp.setEndNode(jpEnd);
				result = rsrsy.save(sp);

				break;
			case "transaction":

				Transaction ts = new Transaction();
				ts.setStartNode(jpStart);
				ts.setEndNode(jpEnd);
				ts.setPercentage(rrbl.getPercentage());

				result = rsrsy.save(ts);
				break;
			case "Customer":

				Customer ct = new Customer();

				ct.setStartNode(jpStart);
				if (jpEnd == null) {
					ct.setEndNode(npEnd);
				} else {
					ct.setEndNode(jpEnd);
				}
				result = rsrsy.save(ct);
				break;

			default:
				break;
			}
		}

		return result;

	}
}
