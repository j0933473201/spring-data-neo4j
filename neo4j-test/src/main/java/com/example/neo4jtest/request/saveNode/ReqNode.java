package com.example.neo4jtest.request.saveNode;

import java.util.List;

public class ReqNode<T> {

	private List<T> nodeList;

	public List<T> getNodeList() {
		return nodeList;
	}

	public void setNodeList(List<T> nodeList) {
		this.nodeList = nodeList;
	}

}
