# Neo4j-圖形化非關連式資料庫

**No sql db可分四大類**
1. Key/value stores
2. Wide column stores
3. Document stores
4. Graph database

**Graph database :**

* Node<——>Node
    1. <-->中間就稱relationship
    2. 透過node與node之間建立關聯,就可產生關連的圖形
    3. 當我要找到某一個關聯點的資料即可直接取得

* 優點：
    1. 任意時間可以將任意的加任意的點與關係,進而提升查找效率
    2. 以往的關聯資料庫若是要查找相對複雜關係的資料,要用到的查詢SQL也相對複雜甚至無法達到目的且效率上也會受到影響

* **啟動neo4j**:
    * 安裝方式:
		1. docker image
		2. 下載官網安裝
	* 啟動:
		1. docker run —name testneo4j -p 7474:7474 -p 7687:7687 neo4j:latest
		2. http://localhost:7474/browser/
		3. 預設帳號:neo4j , 預設密碼: neo4j ,第一次登入後會要求更改密碼

* **操作neo4j**:
	* Cypher:
		1. 是專門為圖形資料庫設計的語言,利用create,match,merge,delete,set...
		2. 搭配 ()代表節點, []代表關係, {}代表屬性 , >代表關係的歸屬也可稱為方向
		3. 口訣：創建節點,同時建立關係,設計查詢以及查詢節點,詳細實作說明參考下列

* **建立資料庫步驟**
    1. 思考node的結構和種類
     Ex: 學生的資料庫需要哪些node
	* 每個人的生日(年)(月)(日)
	* 住址(台灣)(市)(區)(里)
	* 性別(男)(女)
    2. 關聯(relation)
    3. 屬性(properties)
    Ex: 
	* id:886
	* name: 台灣

* 常用函式說明:
    1. Create :新增,不管有沒有存在過,都幫你建
    2. MATCH :類似於Select
    3. MERGE: 
    4. DELETE:刪除
    5. SET 修改

* **指令:**
	* **新增node:**
	    * Ex : Create ( c:Country {id: 886 ,name: “台灣”} )
		* 若需要 添增properties 不可用Create ( c:Country {id: 886 ,name: “台灣”, ename:”Taiwan”} )
	* **刪除node:**
	  	* Ex: MATCH (c: Country{ {id: 886 ,name: “台灣”, ename:”Taiwan”}) 
	       		DELETE c
	* **查詢node:**
		* Ex: MATCH  (c)-[:關聯]->(b)
			where b.屬性 =“xxx”
			return b
	* **添增properties:**
        * Ex : MATCH (c: Country{ {id: 886 ,name: “台灣” }) 
		 	SET c.ename=“Taiwan”

* 取得資料表後,存成csv檔,並且存進Neo4j
* (若是事先有在預設讀取的資料夾內新增一個import的folder,並且將該csv文件放入)
* (若是用 docker container 起的neo4j,則擺放路徑會是在 /var/lib/neo4j/import/)
* (可以用 docker exec -it “container_name/id” bath 到裡面檢視)
    * Ex: LOAD CSV WITH HEADERS FROM “file:///xxx.csv” AS row 
		MATCH (c:Country {id: 886, name: “台灣” , ename: “Taiwan”})
		WITH c , row
		MERGE (d:City{ id: row.id ,name: row.name ,longitude: toFloat(row.longitude), latitude: toFloat(row.latitude)})
		MERGE (d)-[r:BELONG {create_by: “West”} ]->(c)
		
	//第一行代表:將資料內容讀取進來,(row : 代表一列一列讀入進來)
	//第二行代表: 呼叫Country這個node,MATCH () :代表建立關聯
	/ / 第三行代表 提取c ,row的 紀錄資料
	//第四行代表: MERGER(),沒有就幫你補,CREATE,不管有沒有就幫你建
		一樣設定該屬性,並用剛剛提取的row的id,name來當作該City的id,name
	//因為預設都是文字所以,再將經緯度轉為浮點數
	//第五行代表:建立node與node之間的關聯,()->node,[]->關聯
	//“ ->(c) ” 代表 d屬於c