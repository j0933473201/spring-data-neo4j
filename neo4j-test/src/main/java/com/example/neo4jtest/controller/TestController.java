package com.example.neo4jtest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.neo4jtest.nodeEntity.JuristicPerson;
import com.example.neo4jtest.nodeEntity.NaturalPerson;
import com.example.neo4jtest.request.ReqBody;
import com.example.neo4jtest.request.saveNode.ReqNode;
import com.example.neo4jtest.respository.JuristicPersonRepository;
import com.example.neo4jtest.respository.NaturalPersonRepository;

@RestController
@RequestMapping("/")
public class TestController {

	@Autowired
	private JuristicPersonRepository jpry;

	@Autowired
	private NaturalPersonRepository npry;

	/*
	 * http://localhost:8080/neo4j/saveJuristic/Req 儲存法人資訊 
	 * 案例狀況:
	 * 直接提供法人及其關係階層的相關node,可直接建立relationship以及node 
	 * ＃總共執行4件事 
	 * 1.創建法人Node
	 * 2.創建「負責人關聯」以及對應的負責人(自然人Node) 
	 * 3.創建「持有股份關聯」以及對應的持有人(自然人Node)
	 * 4.創建「監察人關聯」以及對應的監察人(自然人Node)
	 * 
	 * Ex: {
	 * 
	 * "name": "xxx", "responPerson": { "name": "xxx" }, "holdingsPerson": [ {
	 * "name": "xxx" }, { "name": "xxx" } ], "supervisor":{ "name":"xxx" }
	 * 
	 * }
	 */
	@PostMapping(path = "saveJuristic/Req")
	public JuristicPerson saveJuristicPerson(@RequestBody JuristicPerson rb) {

		return jpry.save(rb);

	}

	/*
	 * http://localhost:8080/neo4j/saveJuristic/Search/NaturalPersonType 
	 * 儲存法人資訊
	 * 案例狀況: 
	 * 提供法人名字及與有關聯的Node名稱,透過查找,並建立之間關係 
	 * ＃總共執行3件事 
	 * 1.先查找是否有提供的負責人Node(自然人)已被建立
	 * 2.將取得的Node名稱加進法人關聯屬性中(這裡使用負責人) 
	 * 3.創建「負責人關聯」對應的負責人(自然人Node) 
	 * Ex: 
	 * 	{
	 * 	"naturalPerson": { "name":"xxx" }, 
	 * 	"name":"xxx"
	 * 	}
	 */

	@PostMapping(path = "saveJuristic/Search/NaturalPersonType")
	public JuristicPerson saveJuristicPerson(@RequestBody ReqBody body) {

		NaturalPerson np = body.getNaturalPerson();
		NaturalPerson npResult= npry.findByName(np.getName());

		JuristicPerson jp = new JuristicPerson();
		// 如果有被創建
	if (npResult != null) {

			jp.setName(body.getName());
			
				jp.setResponPerson(npResult);
			
		} else {
			NaturalPerson n = new NaturalPerson(np.getName());

			npry.save(n);
			jp.setName(body.getName());
			jp.setResponPerson(n);
		}

		return jpry.save(jp);

	}

	/*
	 * 純建立自然人Node http://localhost:8080/neo4j/saveNaturalPerson/list 
	 * {
	 * "nodeList": [ {"name": "林XX"} , {"name":"xxx"} ] 
	 * }
	 * 
	 */
	@PostMapping(path = "saveNaturalPerson/list")
	public Iterable<NaturalPerson> saveNaturalPersonList(@RequestBody ReqNode<NaturalPerson> reqNP) {

		List<NaturalPerson> npList = reqNP.getNodeList();

		return npry.saveAll(npList);

	}

	/*
	 * 純建立法人Node http://localhost:8080/neo4j/saveJuristicPerson/list 
	 * {
	 * "nodeList": [ 
	 * 		{"name":"TSWW 投資有限公司"} , 
	 * 		{"name":"TSOO 投資有限公司"} 
	 * 	] 
	 * }
	 * 
	 */
	@PostMapping(path = "saveJuristicPerson/list")
	public Iterable<JuristicPerson> saveJuristicPersonList(@RequestBody ReqNode<JuristicPerson> reqNP) {

		List<JuristicPerson> npList = reqNP.getNodeList();

		return jpry.saveAll(npList);

	}

}
